package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.vo.MessageVO;

/**
 * @ClassName: MessageNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 17:10
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface MessageService {
    /**
     * 获取用户邮箱信息
     *
     * @param page   页面
     * @param userId 用户Id
     * @return {@link IPage<MessageVO>}
     */
    IPage<MessageVO> pagingByUserId(Page page, long userId);

    /**
     * 发送
     *
     * @param messageVO 消息签证官
     */
    void send(MessageVO messageVO);

    /**
     * 未读消息数量
     * @param userId
     * @return
     */
    int unread4Me(long userId);

    /**
     * 标记为已读
     * @param userId
     */
    void readed4Me(long userId);

    /**
     * 根据文章ID清理消息
     * @param postId
     * @return
     */
    int deleteByPostId(long postId);
}
