package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Post;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PostMapper
 * @Auther: Jerry
 * @Date: 2020/4/3 9:00
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PostMapper extends BaseMapper<Post> {

    int maxWeight();

    void updateViews(@Param("id") long id, @Param("increment") int increment);

    void updateComments(@Param("id") long id, @Param("increment") int increment);

    void updateFavors(@Param("id") long postId, @Param("increment") int increment);
}
