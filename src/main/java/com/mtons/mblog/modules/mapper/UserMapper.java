package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * @ClassName: UserMapper
 * @Auther: Jerry
 * @Date: 2020/4/3 9:00
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    void updatePosts(@Param("id") Long userId, @Param("increment") int i);

    void updateComments(@Param("ids") Collection<Long> ids, @Param("increment") int i);
}
