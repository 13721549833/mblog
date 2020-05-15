package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.pojo.Options;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OptionsNewService
 * @Auther: Jerry
 * @Date: 2020/4/9 9:25
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface OptionsService {

    /**
     * 查询所有配置
     * @return list
     */
    List<Options> findAll();

    /**
     * 添加或修改配置
     * - 修改时根据key判断唯一性
     * @param options
     */
    void update(Map<String, String> options);

    /**
     * 初始化设置
     *
     * @param resource 资源
     */
    void initSettings(Resource resource);
}
