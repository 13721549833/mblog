package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.annotation.RedisCache;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.base.utils.MarkdownUtils;
import com.mtons.mblog.base.utils.PreviewTextUtils;
import com.mtons.mblog.base.utils.ResourceLock;
import com.mtons.mblog.config.RabbitConfig;
import com.mtons.mblog.modules.aspect.PostStatusFilter;
import com.mtons.mblog.modules.event.PostUpdateEvent;
import com.mtons.mblog.modules.mapper.PostAttributeMapper;
import com.mtons.mblog.modules.mapper.PostMapper;
import com.mtons.mblog.modules.mapper.PostResourceMapper;
import com.mtons.mblog.modules.mapper.ResourceMapper;
import com.mtons.mblog.modules.pojo.*;
import com.mtons.mblog.modules.rabbitmq.PostMqMessage;
import com.mtons.mblog.modules.rabbitmq.RabbitConstant;
import com.mtons.mblog.modules.repository.ArticlesRepository;
import com.mtons.mblog.modules.service.*;
import com.mtons.mblog.modules.vo.PostVO;
import com.mtons.mblog.modules.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: PostNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/4 10:30
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostAttributeMapper postAttributeMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private PostResourceMapper postResourceMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    @PostStatusFilter
    public IPage<PostVO> paging(Page page, String orderBy, String order, int channelId, Set<Integer> excludeChannelIds) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        if (channelId > Consts.ZERO) {
            queryWrapper.eq("channel_id", channelId);
        }
        if (CollectionUtils.isNotEmpty(excludeChannelIds)) {
            queryWrapper.notIn("channel_id", excludeChannelIds);
        }
        if (StringUtils.isNotEmpty(order)) {
            String[] orders = BeanMapUtil.postOrder(order);
            if (Consts.orderBy.DESC.equals(orderBy)) {
                queryWrapper.orderByDesc(orders[0], orders[1]);
            }
            if (Consts.orderBy.ASC.equals(orderBy)) {
                queryWrapper.orderByAsc(orders[0], orders[1]);
            }
        }
        List<Post> postList = postMapper.selectPage(page, queryWrapper).getRecords();
        page.setRecords(toPosts(postList));
        return page;
    }

    @Override
    public IPage<PostVO> paging4Admin(Page page, int channelId, String title) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 0);
        if (channelId > Consts.ZERO) {
            queryWrapper.eq("channel_id", channelId);
        }
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("title", title);
        }
        List records = postMapper.selectPage(page, queryWrapper).getRecords();
        return page.setRecords(toPosts(records));
    }

    @Override
    @PostStatusFilter
    public IPage<PostVO> pagingByAuthorId(Page page, long userId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Consts.STATUS_NORMAL).eq("author_id", userId);
        List<Post> list = postMapper.selectPage(page, queryWrapper).getRecords();
        page.setRecords(toPosts(list));
        return page;
    }

    @Override
    @PostStatusFilter
    @RedisCache
    public List<PostVO> findLatestPosts(int maxResults) {
        return find("created", maxResults).stream().map(BeanMapUtil::copy).collect(Collectors.toList());
    }

    @Override
    @PostStatusFilter
    @RedisCache
    public List<PostVO> findHottestPosts(int maxResults) {
        return find("views", maxResults).stream().map(BeanMapUtil::copy).collect(Collectors.toList());
    }

    @Override
    public Map<Long, PostVO> findMapByIds(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<Post> postList = postMapper.selectBatchIds(ids);
        Map<Long, PostVO> retMap = new HashMap<>();
        HashSet<Long> uids = new HashSet<>();
        postList.forEach(post -> {
            retMap.put(post.getId(), BeanMapUtil.copy(post));
            uids.add(post.getAuthorId());
        });

        // 加载用户信息
        buildUsers(retMap.values(), uids);
        return retMap;
    }

    private void buildUsers(Collection<PostVO> posts, HashSet<Long> uids) {
        if (CollectionUtils.isNotEmpty(uids)) {
            Map<Long, UserVO> userMap = userService.findMapByIds(uids);
            posts.forEach(postVO -> postVO.setAuthor(userMap.get(postVO.getAuthorId())));
        }
    }

    @Override
    @Transactional
    public long post(PostVO post) {
        Post po = new Post();
        BeanUtils.copyProperties(post, po);
        po.setStatus(post.getStatus());
		// 处理摘要
        if (StringUtils.isBlank(post.getSummary())) {
            po.setSummary(trimSummary(post.getEditor(), post.getContent()));
        } else {
            po.setSummary(post.getSummary());
        }
        postMapper.insert(po);

        tagService.batchUpdate(po.getTags(), po.getId());

        String key = ResourceLock.getPostKey(po.getId());
        AtomicInteger lock = ResourceLock.getAtomicInteger(key);
        try {
            synchronized (lock){
                PostAttribute attr = new PostAttribute();
                attr.setContent(post.getContent());
                attr.setEditor(post.getEditor());
                attr.setPostId(po.getId());
                postAttributeMapper.insert(attr);

                countResource(po.getId(), null,  attr.getContent());
                onPushEvent(po, PostUpdateEvent.ACTION_PUBLISH);
                // 发送消息
                rabbitmqSend(po, PostMqMessage.CREATE_OR_UPDATE);
                return po.getId();
            }
        }finally {
            ResourceLock.giveUpAtomicInteger(key);
        }
    }

    /**
     * rabbitmq发送
     *
     * @param po   文章实体对象
     * @param type 类型：CREATE_OR_UPDATE 创建or更新索引；REMOVE 删除索引
     */
    private void rabbitmqSend(Post po, String type) {
        amqpTemplate.convertAndSend(RabbitConstant.ES_EXCHAGE, RabbitConstant.ES_ROUTING_KEY,
                        new PostMqMessage(po.getId(), type));
    }

    private void onPushEvent(Post po, int action) {
        PostUpdateEvent event = new PostUpdateEvent(System.currentTimeMillis());
        event.setPostId(po.getId());
        event.setUserId(po.getAuthorId());
        event.setAction(action);
        applicationContext.publishEvent(event);
    }

    private void countResource(long postId, String originContent, String newContent) {
        if (StringUtils.isEmpty(originContent)){
            originContent = "";
        }
        if (StringUtils.isEmpty(newContent)){
            newContent = "";
        }

        Set<String> exists = extractImageMd5(originContent);
		Set<String> news = extractImageMd5(newContent);

        List<String> adds = ListUtils.removeAll(news, exists);
        List<String> deleteds = ListUtils.removeAll(exists, news);

        if (adds.size() > 0) {
            List<Resource> resources = resourceMapper.selectList(new QueryWrapper<Resource>().in("md5", adds));

			List<PostResource> prs = resources.stream().map(n -> {
				PostResource pr = new PostResource();
				pr.setResourceId(n.getId());
				pr.setPostId(postId);
				pr.setPath(n.getPath());
				return pr;
			}).collect(Collectors.toList());

            postResourceMapper.insertBatch(prs);

            resourceMapper.updateAmount(adds, 1);
        }

        if (deleteds.size() > 0) {
            List<Resource> resources = resourceMapper.selectList(new QueryWrapper<Resource>().in("md5", deleteds));
            List<Long> rids = resources.stream().map(Resource::getId).collect(Collectors.toList());
            postResourceMapper.delete(new QueryWrapper<PostResource>().eq("post_id", postId).in("resource_id", rids));
        }
    }

    private Set<String> extractImageMd5(String text) {
        Pattern pattern = Pattern.compile("(?<=/_signature/)(.+?)(?=\\.)");
//		Pattern pattern = Pattern.compile("(?<=/_signature/)[^/]+?jpg");

        Set<String> md5s = new HashSet<>();

        Matcher originMatcher = pattern.matcher(text);
        while (originMatcher.find()) {
            String key = originMatcher.group();
//			md5s.add(key.substring(0, key.lastIndexOf(".")));
            md5s.add(key);
        }
        return md5s;
    }

    private String trimSummary(String editor, String text) {
        if (Consts.EDITOR_MARKDOWN.endsWith(editor)) {
            return PreviewTextUtils.getText(MarkdownUtils.renderMarkdown(text), 126);
        } else {
            return PreviewTextUtils.getText(text, 126);
        }
    }

    private List<PostVO> toPosts(List<Post> posts) {
        List<PostVO> rets = new ArrayList<>();

		HashSet<Long> uids = new HashSet<>();
		HashSet<Integer> groupIds = new HashSet<>();

		posts.forEach(po -> {
			uids.add(po.getAuthorId());
			groupIds.add(po.getChannelId());
			rets.add(BeanMapUtil.copy(po));
		});

		// 加载用户信息
		buildUsers(rets, uids);
		buildGroups(rets, groupIds);

        return rets;
    }

    private void buildGroups(List<PostVO> posts, Set<Integer> groupIds){
        if (CollectionUtils.isNotEmpty(groupIds)) {
            Map<Integer, Channel> map = channelService.findMapByIds(groupIds);
            posts.forEach(p -> p.setChannel(map.get(p.getChannelId())));
        }
    }

    @Override
    @RedisCache
    public PostVO get(long id) {
        Post post = getPostById(id);
        if (post != null) {
            PostVO postVO = BeanMapUtil.copy(post);
            postVO.setAuthor(userService.get(postVO.getAuthorId()));
            postVO.setChannel(channelService.getById(postVO.getChannelId()));
            PostAttribute attr = postAttributeMapper.selectOne(new QueryWrapper<PostAttribute>()
                    .eq("post_id", postVO.getId()));
            postVO.setContent(attr.getContent());
            postVO.setEditor(attr.getEditor());
            return postVO;
        }
        return null;
    }

    @Override
    public Post getPostById(long id) {
        return postMapper.selectById(id);
    }

    @Override
    @Transactional
    public void update(PostVO p) {
        Post po = getPostById(p.getId());
        if (po != null) {
            String key = ResourceLock.getPostKey(p.getId());
            AtomicInteger lock = ResourceLock.getAtomicInteger(key);
            try {
                synchronized (lock) {
                    po.setTitle(p.getTitle());
                    po.setChannelId(p.getChannelId());
                    po.setThumbnail(p.getThumbnail());
                    po.setStatus(p.getStatus());
                    // 处理摘要
                    if (StringUtils.isBlank(p.getSummary())) {
                        po.setSummary(trimSummary(p.getEditor(), p.getContent()));
                    } else {
                        po.setSummary(p.getSummary());
                    }
                    // 标签
                    po.setTags(p.getTags());
                    postMapper.updateById(po);

                    PostAttribute postAttribute = getPostAttribute(po.getId());
                    String originContent = "";
                    if (postAttribute != null) {
                        originContent = postAttribute.getContent();
                    }
                    PostAttribute attr = new PostAttribute();
                    attr.setContent(p.getContent());
                    attr.setEditor(p.getEditor());
                    attr.setPostId(po.getId());
                    attr.setId(postAttribute.getId());
                    postAttributeMapper.updateById(attr);

                    tagService.batchUpdate(po.getTags(), po.getId());
                    countResource(po.getId(), originContent, p.getContent());
                    // 发送消息
                    rabbitmqSend(po, PostMqMessage.CREATE_OR_UPDATE);
                }
            } finally {
                ResourceLock.giveUpAtomicInteger(key);
            }
        }
    }

    private PostAttribute getPostAttribute(long postId){
        QueryWrapper<PostAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        return postAttributeMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void updateFeatured(long id, int featured) {
        Post post = getPostById(id);

        int status = Consts.FEATURED_ACTIVE == featured ? Consts.FEATURED_ACTIVE : Consts.FEATURED_DEFAULT;
        post.setFeatured(status);
        postMapper.updateById(post);
        // 发送消息
        rabbitmqSend(post, PostMqMessage.CREATE_OR_UPDATE);
    }

    @Override
    @Transactional
    public void updateWeight(long id, int weighted) {
        Post post = getPostById(id);

        int max = Consts.ZERO;
        if (Consts.FEATURED_ACTIVE == weighted) {
            max = postMapper.maxWeight() + 1;
        }
        post.setWeight(max);
        postMapper.updateById(post);
    }

    @Override
    @Transactional
    public void delete(long id, long authorId) {
        Post post = getPostById(id);
        // 判断文章是否属于当前登录用户
        Assert.isTrue(post.getAuthorId() == authorId, "认证失败");
        String key = ResourceLock.getPostKey(post.getId());
        AtomicInteger lock = ResourceLock.getAtomicInteger(key);
        try {
            synchronized (lock) {
                postMapper.deleteById(id);
                postAttributeMapper.deleteById(id);
                cleanResource(post.getId());
                onPushEvent(post, PostUpdateEvent.ACTION_DELETE);
                // 发送消息
                rabbitmqSend(post, PostMqMessage.REMOVE);
            }
        } finally {
            ResourceLock.giveUpAtomicInteger(key);
        }
    }

    @Override
    @Transactional
    public void delete(Collection<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<Post> postList = postMapper.selectBatchIds(ids);
            postList.forEach(post -> {
                String key = ResourceLock.getPostKey(post.getId());
                AtomicInteger lock = ResourceLock.getAtomicInteger(key);
                try {
                    synchronized (lock){
                        postMapper.deleteById(post.getId());
                        postAttributeMapper.deleteById(post.getId());
                        cleanResource(post.getId());
                        onPushEvent(post, PostUpdateEvent.ACTION_DELETE);
                        // 发送消息
                        rabbitmqSend(post, PostMqMessage.REMOVE);
                    }
                } finally {
                    ResourceLock.giveUpAtomicInteger(key);
                }
            });
        }
    }

    @Override
    @Transactional
    public void identityViews(long id) {
        // 次数不清理缓存, 等待文章缓存自动过期
        Post post = getPostById(id);

        postMapper.updateViews(id, Consts.IDENTITY_STEP);
        post.setViews(post.getViews() + Consts.IDENTITY_STEP);
        // 发送消息
        rabbitmqSend(post, PostMqMessage.CREATE_OR_UPDATE);
    }

    @Override
    @Transactional
    public void identityComments(long id) {
        Post post = getPostById(id);

        postMapper.updateComments(id, Consts.IDENTITY_STEP);
        post.setComments(post.getComments() + Consts.IDENTITY_STEP);
        // 发送消息
        rabbitmqSend(post, PostMqMessage.CREATE_OR_UPDATE);
    }

    @Override
    @Transactional
    public void favor(long userId, long postId) {
        postMapper.updateFavors(postId, Consts.IDENTITY_STEP);
        favoriteService.add(userId, postId);
    }

    @Override
    @Transactional
    public void unfavor(long userId, long postId) {
        postMapper.updateFavors(postId, Consts.DECREASE_STEP);
        favoriteService.delete(userId, postId);
    }

    @Override
    @PostStatusFilter
    public long count() {
        return postMapper.selectCount(null);
    }

    private void cleanResource(long postId) {
        QueryWrapper<PostResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        List<PostResource> list = postResourceMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<Long> rids = list.stream().map(PostResource::getResourceId).collect(Collectors.toList());
        resourceMapper.updateAmountByIds(rids, -1);
        postResourceMapper.delete(queryWrapper);
    }

    @PostStatusFilter
    private List<Post> find(String orderBy, int size) {
        Page page = new Page(1, size);
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(orderBy);
        Set<Integer> excludeChannelIds = new HashSet<>();
        List<Channel> channelList = channelService.findAll(Consts.STATUS_CLOSED);
        if (CollectionUtils.isNotEmpty(channelList)) {
            channelList.forEach((c) -> excludeChannelIds.add(c.getId()));
        }

        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        if (excludeChannelIds.size() > 0) {
            queryWrapper.notIn("channel_id", excludeChannelIds);
        }
        IPage iPage = postMapper.selectPage(page, queryWrapper);
        return iPage.getRecords();
    }
}
