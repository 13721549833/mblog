package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mtons.mblog.modules.pojo.Articles;
import com.mtons.mblog.modules.rabbitmq.PostMqMessage;

/**
 * @ClassName: PostSearchNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 18:09
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PostSearchService {

    /**
     * 根据关键字搜索
     * @param page 页码
     * @param size 页大小
     * @param term 关键字
     * @throws Exception
     */
    IPage<Articles> search(int page, int size, String term) throws Exception;

    /**
     * 重建
     */
    void resetIndexes();

    /**
     * 创建或更新索引
     *
     * @param message 消息
     */
    void createOrUpdateIndex(PostMqMessage message);

    /**
     * 删除索引
     *
     * @param message 消息
     */
    void removeIndex(PostMqMessage message);
}
