package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Message;

/**
 * @ClassName: MessageMapper
 * @Auther: Jerry
 * @Date: 2020/4/17 17:14
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface MessageMapper extends BaseMapper<Message> {
    void updateReadedByUserId(long userId);
}
