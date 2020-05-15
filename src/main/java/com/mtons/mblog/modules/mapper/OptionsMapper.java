package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Options;

import java.util.List;

/**
 * @ClassName: OptionsMapper
 * @Auther: Jerry
 * @Date: 2020/4/9 9:23
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface OptionsMapper extends BaseMapper<Options> {

    List<Options> findAll();
}
