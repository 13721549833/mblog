package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.pojo.Channel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ChannelNewService
 * @Auther: Jerry
 * @Date: 2020/4/8 10:37
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface ChannelService {

    /**
     * 获取所有栏目
     *
     * @param status 状态
     * @return {@link List<Channel>}
     */
    List<Channel> findAll(int status);

    /**
     * 查询单个栏目
     *
     * @param channelId 通道标识
     * @return {@link Channel}
     */
    Channel getById(int channelId);

    /**
     * 根据ids查询栏目
     *
     * @param ids id集合
     * @return {@link Map<Integer, Channel>}
     */
    Map<Integer, Channel> findMapByIds(Set<Integer> ids);

    /**
     * 更新栏目
     *
     * @param channel 通道
     */
    void update(Channel channel);

    /**
     * 更新权重
     *
     * @param id       id
     * @param weighted 加权
     */
    void updateWeight(int id, int weighted);

    /**
     * 删除栏目
     *
     * @param id id
     */
    void delete(int id);

    /**
     * 栏目总数
     *
     * @return long
     */
    long count();
}
