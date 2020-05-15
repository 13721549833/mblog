package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.modules.mapper.LinksMapper;
import com.mtons.mblog.modules.pojo.Links;
import com.mtons.mblog.modules.service.LinksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: LinksNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 17:03
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksMapper linksMapper;

    @Override
    public List<Links> findAll() {
        QueryWrapper<Links> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return linksMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Links links) {
        Links po = linksMapper.selectById(links.getId());
        BeanUtils.copyProperties(links, po, "created", "updated");
        linksMapper.insert(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        linksMapper.deleteById(id);
    }
}
