/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.base.storage.StorageFactory;
import com.mtons.mblog.base.utils.MD5;
import com.mtons.mblog.config.SiteOptions;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.vo.AccountProfile;
import com.mtons.mblog.web.formatter.StringEscapeEditor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller 基类
 *
 * @author langhsu
 * @since 3.0
 */
@Slf4j
public class BaseController {
    @Autowired
    protected StorageFactory storageFactory;
    @Autowired
    protected SiteOptions siteOptions;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    protected AccountProfile getProfile(){
        Subject subject = SecurityUtils.getSubject();
        Long userId = (Long) subject.getPrincipal();
        return userService.findProfile(userId);
    }

    /**
     * 初始化分页信息
     *
     * @param request 请求
     * @return {@link Page}
     */
    protected Page getPage(HttpServletRequest request){
        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Consts.PAGE_DEFAULT_SIZE);
        return new Page(pageNo, pageSize);
    }

    protected void putProfile(AccountProfile profile){
        SecurityUtils.getSubject().getSession(true).setAttribute("profile", profile);
    }

    protected boolean isAuthenticated() {
        return SecurityUtils.getSubject() != null && (SecurityUtils.getSubject().isAuthenticated() || SecurityUtils.getSubject().isRemembered());
    }

    protected String view(String view) {
        return "/" + siteOptions.getValue("theme") + view;
    }

    protected Result<AccountProfile> executeLogin(String username, String password, boolean rememberMe) {
        Result<AccountProfile> ret = Result.failure("登录失败");

        if (StringUtils.isAnyBlank(username, password)) {
            return ret;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.md5(password), rememberMe);

        try {
            SecurityUtils.getSubject().login(token);
            ret = Result.success(getProfile());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            ret = Result.failure("用户不存在");
        } catch (LockedAccountException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            ret = Result.failure("用户被禁用");
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            ret = Result.failure("用户认证失败");
        }
        return ret;
    }

    public static void main(String[] args) {
        String password = MD5.md5("123456");
        System.out.println(password);
    }
}
