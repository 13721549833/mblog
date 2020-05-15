package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.modules.mapper.OptionsMapper;
import com.mtons.mblog.modules.pojo.Options;
import com.mtons.mblog.modules.service.OptionsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OptionsNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/9 9:25
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapper optionsMapper;

    @Autowired
	private SqlSessionFactory sqlSessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Options> findAll() {
        return optionsMapper.findAll();
    }

    @Override
	@Transactional(rollbackFor = Exception.class)
    public void update(Map<String, String> options) {
        if (options == null || options.isEmpty()) {
			return;
		}

		options.forEach((key, value) -> {
            QueryWrapper<Options> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("key_", key);
		    Options entity = optionsMapper.selectOne(queryWrapper);
			String val = StringUtils.trim(value);
			if (entity != null) {
				entity.setValue(val);
				optionsMapper.updateById(entity);
			} else {
				entity = new Options();
				entity.setKey(key);
				entity.setValue(val);
				optionsMapper.insert(entity);
			}
		});
    }

	@Override
	public void initSettings(Resource resource) {
	}
}
