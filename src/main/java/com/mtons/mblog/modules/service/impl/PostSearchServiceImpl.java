package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.pojo.Articles;
import com.mtons.mblog.modules.pojo.Channel;
import com.mtons.mblog.modules.pojo.Post;
import com.mtons.mblog.modules.rabbitmq.PostMqMessage;
import com.mtons.mblog.modules.repository.ArticlesRepository;
import com.mtons.mblog.modules.service.ChannelService;
import com.mtons.mblog.modules.service.PostSearchService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PostSearchNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 18:10
 * @Desctiption: 文章搜索
 * @Version: 1.0
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class PostSearchServiceImpl implements PostSearchService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PostService postService;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Override
    public IPage<Articles> search(int page, int size, String term) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", term))
                .should(QueryBuilders.matchQuery("summary", term))
                .should(QueryBuilders.matchQuery("tags", term));
        // 创建高亮查询
        NativeSearchQueryBuilder nativeSearchQuery = new NativeSearchQueryBuilder();
        nativeSearchQuery.withQuery(boolQueryBuilder);
        nativeSearchQuery.withHighlightFields(new HighlightBuilder.Field("title"),
                new HighlightBuilder.Field("summary"),
                new HighlightBuilder.Field("tags"));
        nativeSearchQuery.withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"));
        // 设置分页,页码要减1
        nativeSearchQuery.withPageable(PageRequest.of(page - 1, size));
        // 分页对象
        AggregatedPage<Articles> eSearchPage = elasticsearchTemplate.queryForPage(nativeSearchQuery.build(), Articles.class,
                new SearchResultMapper() {
                    @Override
                    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                        ArrayList<Articles> list = new ArrayList<Articles>();
                        SearchHits hits = response.getHits();
                        for (SearchHit searchHit : hits) {
                            if (hits.getHits().length <= 0) {
                                return null;
                            }
                            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                            Integer id = (Integer) sourceAsMap.get("id");
                            String title = (String) sourceAsMap.get("title");
                            Object author = sourceAsMap.get("author");

                            String summary = (String) sourceAsMap.get("summary");
                            String tags = (String) sourceAsMap.get("tags");

                            Object channel = sourceAsMap.get("channel");
                            String thumbnail = (String) sourceAsMap.get("thumbnail");
                            Integer favors = (Integer) sourceAsMap.get("favors");
                            Integer comments = (Integer) sourceAsMap.get("comments");
                            Integer views = (Integer) sourceAsMap.get("views");
                            Integer featured = (Integer) sourceAsMap.get("featured");
                            Date created = new Date((Long) sourceAsMap.get("created"));

                            Articles seArticleVo = new Articles();
                            HighlightField highLightField = searchHit.getHighlightFields().get("title");
                            if (highLightField == null) {
                                seArticleVo.setTitle(title);
                            } else {
                                seArticleVo.setTitle(highLightField.fragments()[0].toString());
                            }
                            highLightField = searchHit.getHighlightFields().get("summary");
                            if (highLightField == null) {
                                seArticleVo.setSummary(summary);
                            } else {
                                seArticleVo.setSummary(highLightField.fragments()[0].toString());
                            }
                            highLightField = searchHit.getHighlightFields().get("tags");
                            if (highLightField == null) {
                                seArticleVo.setTags(tags);
                            } else {
                                seArticleVo.setTags(highLightField.fragments()[0].toString());
                            }
                            highLightField = searchHit.getHighlightFields().get("id");
                            if (highLightField == null) {
                                seArticleVo.setId(id.longValue());
                            } else {
                                seArticleVo.setId(Long.parseLong(highLightField.fragments()[0].toString()));
                            }
                            seArticleVo.setAuthor(author);
                            seArticleVo.setChannel(channel);
                            seArticleVo.setCreated(created);
                            seArticleVo.setThumbnail(thumbnail);
                            seArticleVo.setFavors(favors);
                            seArticleVo.setComments(comments);
                            seArticleVo.setViews(views);
                            seArticleVo.setFeatured(featured == null ? 0 : featured);
                            list.add(seArticleVo);
                        }
                        AggregatedPage<T> pageResult = new AggregatedPageImpl<T>((List<T>) list, pageable, hits.getTotalHits());
                        return pageResult;
                    }
                });
        long pageNum = Long.valueOf(eSearchPage.getNumber());
        long pageSize = Long.valueOf(eSearchPage.getPageable().getPageSize());
        Page page1 = new Page(pageNum, pageSize);
        page1.setRecords(eSearchPage.getContent());
        page1.setTotal(Long.valueOf(eSearchPage.getTotalElements()));
        return page1;
    }

    @Override
    public void resetIndexes() {
        elasticsearchTemplate.deleteIndex(Articles.class);
    }

    @Override
    public void createOrUpdateIndex(PostMqMessage message) {
        long postId = message.getPostId();
        Post post = postService.getPostById(postId);
        Articles articles = BeanMapUtil.post2Articles(post);
        UserVO author = userService.get(post.getAuthorId());
        Channel channel = channelService.getById(post.getChannelId());
        articles.setAuthor(author);
        articles.setChannel(channel);
        articlesRepository.save(articles);
        log.info("es 索引更新成功！ ---> {}", articles.toString());
    }

    @Override
    public void removeIndex(PostMqMessage message) {
        long postId = message.getPostId();

        articlesRepository.deleteById(postId);
        log.info("es 索引删除成功！ ---> {}", message.toString());
    }
}
