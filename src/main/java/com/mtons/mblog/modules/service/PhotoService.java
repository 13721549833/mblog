package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.pojo.Photo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PhotoService
 * @Auther: Jerry
 * @Date: 2020/5/8 17:43
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PhotoService {
    /**
     * 查询照片列表
     *
     * @return {@link Map<String, Object>}
     */
    List<Map<String, Object>> queryPhotoList();

    IPage<Photo> paging(Page page);

    int save(Photo photo);

    void delete(Integer id);
}
