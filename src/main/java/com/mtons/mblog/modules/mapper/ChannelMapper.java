package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Channel;

import java.util.List;

/**
 * @ClassName: ChannelMapper
 * @Auther: Jerry
 * @Date: 2020/4/8 10:40
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    /**
     * 根据状态查询栏目
     *
     * @param status 状态
     * @return {@link List<Channel>}
     */
    List<Channel> selectListByStatus(int status);

    /**
     * 最大权重
     *
     * @return int
     */
    int maxWeight();
}
