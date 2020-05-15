package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.PostResource;

import java.util.List;

/**
 * @ClassName: PostResourceMapper
 * @Auther: Jerry
 * @Date: 2020/4/8 13:48
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PostResourceMapper extends BaseMapper<PostResource> {

    void insertBatch(List<PostResource> resources);
}
