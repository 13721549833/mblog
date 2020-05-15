package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.mapper.ChannelMapper;
import com.mtons.mblog.modules.pojo.Channel;
import com.mtons.mblog.modules.service.ChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ChannelNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/8 10:39
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<Channel> findAll(int status) {
        QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
        List<Channel> list;
        if (status > Consts.IGNORE) {
            list = channelMapper.selectListByStatus(status);
        } else {
            queryWrapper.orderByDesc("weight").orderByAsc("id");
            list = channelMapper.selectList(queryWrapper);
        }
        return list;
    }

    @Override
    public Channel getById(int channelId) {
        return channelMapper.selectById(channelId);
    }

    @Override
    public Map<Integer, Channel> findMapByIds(Set<Integer> groupIds) {
        List<Channel> channelList = channelMapper.selectBatchIds(groupIds);
        Map<Integer, Channel> rets = new HashMap<>();
        channelList.forEach(channel -> rets.put(channel.getId(), channel));
        return rets;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Channel channel) {
        Channel po = channelMapper.selectById(channel.getId());
        if (po == null) {
            po = new Channel();
            BeanUtils.copyProperties(channel, po);
            channelMapper.insert(po);
        } else {
            BeanUtils.copyProperties(channel, po);
            channelMapper.updateById(po);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWeight(int id, int weighted) {
        Channel po = channelMapper.selectById(id);
        int max = Consts.ZERO;
        if (Consts.FEATURED_ACTIVE == weighted) {
            max = channelMapper.maxWeight() + 1;
        }
        po.setWeight(max);
        channelMapper.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        channelMapper.deleteById(id);
    }

    @Override
    public long count() {
        return channelMapper.selectCount(null);
    }
}
