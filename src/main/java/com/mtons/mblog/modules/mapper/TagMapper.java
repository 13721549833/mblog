package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * @ClassName: TagMapper
 * @Auther: Jerry
 * @Date: 2020/4/8 10:40
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface TagMapper extends BaseMapper<Tag> {
    void decrementPosts(@Param("ids") Collection<Long> tagIds);
}
