package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ResourceMapper
 * @Auther: Jerry
 * @Date: 2020/4/8 13:43
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    int updateAmount(@Param("md5s") List<String> md5s, @Param("increment") long increment);

    int updateAmountByIds(@Param("ids") List<Long> ids, @Param("increment") long increment);

    List<Resource> selectByIds(List<Long> ids);
}
