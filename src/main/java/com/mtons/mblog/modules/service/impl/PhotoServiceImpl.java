package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.utils.DateTimeUtils;
import com.mtons.mblog.modules.mapper.PhotoMapper;
import com.mtons.mblog.modules.pojo.Photo;
import com.mtons.mblog.modules.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: PhotoServiceImpl
 * @Auther: Jerry
 * @Date: 2020/5/8 17:44
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Map<String, Object>> queryPhotoList() {
        List<Photo> photos = photoMapper.queryPhotoList();
        List<Map<String, Object>> list = new ArrayList<>();
        // 分组筛选
        Map<String, List<Photo>> resMap = photos.stream().collect(Collectors.groupingBy(photo -> DateTimeUtils.format( photo.getCreated(), "yyyy/MM/dd")));
        // 遍历
        Iterator<Map.Entry<String, List<Photo>>> iterator = resMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = new HashMap<>();
            Map.Entry<String, List<Photo>> entry = iterator.next();
            map.put("created", entry.getKey());
            map.put("photoList", entry.getValue());
            list.add(map);
        }
        // 按降序排序
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String created1 = String.valueOf(o1.get("created").toString());
                String created2 = String.valueOf(o2.get("created").toString());
                return created2.compareTo(created1);
            }
        });
        return list;
    }

    @Override
    public IPage<Photo> paging(Page page) {
        QueryWrapper<Photo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        return photoMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Photo photo) {
        return photoMapper.insert(photo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        photoMapper.deleteById(id);
    }
}
