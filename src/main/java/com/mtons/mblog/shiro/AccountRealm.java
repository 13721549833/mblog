package com.mtons.mblog.shiro;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.pojo.Role;
import com.mtons.mblog.modules.pojo.User;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.service.UserRoleService;
import com.mtons.mblog.modules.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    public AccountRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        UserVO user = userService.get(userId);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = userRoleService.listRoles(user.getId());

            //赋予角色
            roles.forEach(role -> {
                info.addRole(role.getName());

                //赋予权限
                role.getPermissions().forEach(permission -> info.addStringPermission(permission.getName()));
            });
            return info;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = (String) token.getPrincipal();

        User user = userService.getByUserName(username);
        if (null == user) {
            throw new UnknownAccountException(upToken.getUsername());
        }

        if (user.getStatus() == Consts.STATUS_CLOSED) {
            throw new LockedAccountException(user.getName());
        }

        return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), ByteSource.Util.bytes(username), getName());
    }
}
