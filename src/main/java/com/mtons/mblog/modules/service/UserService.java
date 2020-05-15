package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.pojo.User;
import com.mtons.mblog.modules.vo.AccountProfile;
import com.mtons.mblog.modules.vo.UserVO;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName: UserNewService
 * @Auther: Jerry
 * @Date: 2020/4/8 11:06
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface UserService {

    IPage<UserVO> paging(Page page, String name);

    /**
     * 通过id查询用户信息
     *
     * @param ids id
     * @return {@link Map<Long,  UserVO >}
     */
    Map<Long, UserVO> findMapByIds(Set<Long> ids);

    /**
     * 查询单个用户
     *
     * @param userId 用户Id
     * @return {@link UserVO}
     */
    UserVO get(long userId);

    /**
     * 获取用户信息
     *
     * @param id id
     * @return {@link AccountProfile}
     */
    AccountProfile findProfile(long id);

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link UserVO}
     */
    UserVO register(UserVO user);

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return {@link AccountProfile}
     */
    AccountProfile update(UserVO user);

    /**
     * 更新用户头像
     *
     * @param id   id
     * @param path 路径
     * @return {@link AccountProfile}
     */
    AccountProfile updateAvatar(long id, String path);

    /**
     * 更新密码
     *
     * @param id          id
     * @param newPassword 新密码
     */
    void updatePassword(long id, String newPassword);

    /**
     * 更新密码
     *
     * @param id          id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(long id, String oldPassword, String newPassword);

    /**
     * 更新电子邮件
     *
     * @param id    id
     * @param email 电子邮件
     * @return {@link AccountProfile}
     */
    AccountProfile updateEmail(long id, String email);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link AccountProfile}
     */
    AccountProfile login(String username, String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link AccountProfile}
     */
    User getByUserName(String username);

    /**
     * 通过电子邮件获取用户信息
     *
     * @param email 电子邮件
     * @return {@link User}
     */
    User getByEmail(String email);

    /**
     * 修改用户状态
     * @param id
     * @param status 状态
     */
    void updateStatus(long id, int status);

    /**
     * 统计用户数
     *
     * @return long
     */
    long count();
}
