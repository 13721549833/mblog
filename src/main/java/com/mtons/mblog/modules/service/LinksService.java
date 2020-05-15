package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.pojo.Links;

import java.util.List;

/**
 * @ClassName: LinksNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 17:01
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface LinksService {
    /**
     * 找到所有
     *
     * @return {@link List<Links>}
     */
    List<Links> findAll();

    /**
     * 更新
     *
     * @param links 链接
     */
    void update(Links links);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(long id);
}
