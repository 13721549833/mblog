package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.EntityStatus;
import com.mtons.mblog.base.lang.MtonsException;
import com.mtons.mblog.modules.mapper.SecurityCodeMapper;
import com.mtons.mblog.modules.pojo.SecurityCode;
import com.mtons.mblog.modules.service.SecurityCodeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @ClassName: SecurityCodeNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/20 17:13
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
public class SecurityCodeServiceImpl implements SecurityCodeService {

    @Autowired
    private SecurityCodeMapper securityCodeMapper;

    /**
     * 验证码存活时间 单位：分钟
     */
    private int survivalTime = 30;

    @Override
    @Transactional
    public String generateCode(String key, int type, String target) {
        SecurityCode securityCode = securityCodeMapper.selectOne(new QueryWrapper<SecurityCode>().eq("key_", key));

        String code = RandomStringUtils.randomNumeric(6);
        Date now = new Date();

        if (null == securityCode) {
            securityCode = new SecurityCode();
            securityCode.setKey(key);
            securityCode.setExpired(DateUtils.addMinutes(now, survivalTime));
            securityCode.setCode(code);
            securityCode.setType(type);
            securityCode.setTarget(target);
            securityCodeMapper.insert(securityCode);
        } else {
            long interval = ( now.getTime() - securityCode.getCreated().getTime() ) / 1000;
            if (interval <= 60) {
                throw new MtonsException("发送间隔时间不能少于1分钟");
            }
            securityCode.setKey(key);
            // 验证状态置为0
            securityCode.setStatus(EntityStatus.ENABLED);
            securityCode.setExpired(DateUtils.addMinutes(now, survivalTime));
            securityCode.setCode(code);
            securityCode.setType(type);
            securityCode.setTarget(target);
            securityCodeMapper.updateById(securityCode);
        }
        return code;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean verify(String key, int type, String code) {
        Assert.hasLength(code, "验证码不能为空");
        SecurityCode po = securityCodeMapper.selectOne(new QueryWrapper<SecurityCode>().eq("key_", key).eq(
        "type", type));

        Assert.notNull(po, "您没有进行过类型验证");

        Date now = new Date();

        Assert.state(now.getTime() <= po.getExpired().getTime(), "验证码已过期");
        Assert.isTrue(po.getType() == type, "验证码类型错误");
        Assert.isTrue(po.getStatus() == Consts.CODE_STATUS_INIT, "验证码已经使用过");
        Assert.state(code.equals(po.getCode()), "验证码不对");

        po.setStatus(Consts.CODE_STATUS_CERTIFIED);
        securityCodeMapper.updateById(po);
        return true;
    }
}
