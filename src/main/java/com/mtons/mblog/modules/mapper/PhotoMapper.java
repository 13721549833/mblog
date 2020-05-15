package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Photo;

import java.util.List;

/**
 * @ClassName: PhotoMapper
 * @Auther: Jerry
 * @Date: 2020/5/8 17:42
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PhotoMapper extends BaseMapper<Photo> {

    /**
     * 查询照片列表
     *
     * @return {@link List<Photo>}
     */
    List<Photo> queryPhotoList();
}
