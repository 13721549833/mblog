package com.mtons.mblog.modules.service;

/**
 * @ClassName: SecurityCodeNewService
 * @Auther: Jerry
 * @Date: 2020/4/20 17:13
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface SecurityCodeService {

    /**
     * 生成验证码
     * @param key
     * @param target : email mobile
     * @return
     */
    String generateCode(String key, int type, String target);

    /**
     * 检验验证码有效性
     * @param key
     * @param code
     * @return token
     */
    boolean verify(String key, int type, String code);
}
