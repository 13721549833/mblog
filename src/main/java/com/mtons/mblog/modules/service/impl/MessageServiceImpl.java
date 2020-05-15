package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.mapper.MessageMapper;
import com.mtons.mblog.modules.pojo.Message;
import com.mtons.mblog.modules.service.MessageService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.vo.MessageVO;
import com.mtons.mblog.modules.vo.PostVO;
import com.mtons.mblog.modules.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: MessageNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 17:13
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @Override
    public IPage<MessageVO> pagingByUserId(Page page, long userId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        IPage<Message> iPage = messageMapper.selectPage(page, queryWrapper);
        List<MessageVO> rets = new ArrayList<>();

        Set<Long> postIds = new HashSet<>();
        Set<Long> fromUserIds = new HashSet<>();

        // 筛选
        iPage.getRecords().forEach(po -> {
            MessageVO no = BeanMapUtil.copy(po);

            rets.add(no);

            if (no.getPostId() > 0) {
                postIds.add(no.getPostId());
            }
            if (no.getFromId() > 0) {
                fromUserIds.add(no.getFromId());
            }

        });

        // 加载
        Map<Long, PostVO> posts = postService.findMapByIds(postIds);
        Map<Long, UserVO> fromUsers = userService.findMapByIds(fromUserIds);

        rets.forEach(n -> {
            if (n.getPostId() > 0) {
                n.setPost(posts.get(n.getPostId()));
            }
            if (n.getFromId() > 0) {
                n.setFrom(fromUsers.get(n.getFromId()));
            }
        });

        page.setRecords(rets);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(MessageVO message) {
        if (message == null || message.getUserId() <=0 || message.getFromId() <= 0) {
            return;
        }

        Message po = new Message();
        BeanUtils.copyProperties(message, po);

        messageMapper.insert(po);
    }

    @Override
    public int unread4Me(long userId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("status", Consts.UNREAD);
        return messageMapper.selectCount(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readed4Me(long userId) {
        messageMapper.updateReadedByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPostId(long postId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        return messageMapper.delete(queryWrapper);
    }
}
