package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.EntityStatus;
import com.mtons.mblog.base.lang.MtonsException;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.base.utils.MD5;
import com.mtons.mblog.modules.mapper.UserMapper;
import com.mtons.mblog.modules.pojo.User;
import com.mtons.mblog.modules.service.MessageService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.vo.AccountProfile;
import com.mtons.mblog.modules.vo.BadgesCount;
import com.mtons.mblog.modules.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @ClassName: UserNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/8 11:07
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public IPage<UserVO> paging(Page page, String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        List<UserVO> rets = new ArrayList<>();
        iPage.getRecords().forEach(n -> rets.add(BeanMapUtil.copy(n)));
        page.setRecords(rets);
        return page;
    }

    @Override
    public Map<Long, UserVO> findMapByIds(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<User> list = userMapper.selectList(new QueryWrapper<User>().in("id", ids));
        Map<Long, UserVO> ret = new HashMap<>();
        list.forEach(user -> ret.put(user.getId(), BeanMapUtil.copy(user)));
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountProfile login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Consts.STATUS_NORMAL).eq("username", username);
        User po = userMapper.selectOne(queryWrapper);

        if (null == po) {
            return null;
        }

        Assert.state(StringUtils.equals(po.getPassword(), password), "密码错误");

        po.setLastLogin(Calendar.getInstance().getTime());
        userMapper.updateById(po);
        AccountProfile u = BeanMapUtil.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);
        return u;
    }

    @Override
    public UserVO get(long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return BeanMapUtil.copy(user);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountProfile findProfile(long id) {
        User po = userMapper.selectById(id);

        Assert.notNull(po, "账户不存在");

        po.setLastLogin(Calendar.getInstance().getTime());

        AccountProfile u = BeanMapUtil.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);

        return u;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVO register(UserVO user) {
        Assert.notNull(user, "Parameter user can not be null!");

        Assert.hasLength(user.getUsername(), "用户名不能为空!");
        Assert.hasLength(user.getPassword(), "密码不能为空!");

        User check = getByUserName(user.getUsername());

        Assert.isNull(check, "用户名已经存在!");

        if (StringUtils.isNotBlank(user.getEmail())) {
            User emailCheck = getByEmail(user.getEmail());
            Assert.isNull(emailCheck, "邮箱已经存在!");
        }

        User po = new User();

        BeanUtils.copyProperties(user, po);

        if (StringUtils.isBlank(po.getName())) {
            po.setName(user.getUsername());
        }

        po.setPassword(MD5.md5(user.getPassword()));
        po.setStatus(EntityStatus.ENABLED);

        userMapper.insert(po);
        return BeanMapUtil.copy(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountProfile update(UserVO user) {
        User po = getUser(user.getId());
        po.setName(user.getName());
        po.setSignature(user.getSignature());
        userMapper.updateById(po);
        return BeanMapUtil.copyPassport(po);
    }

    private User getUser(long id){
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountProfile updateAvatar(long id, String path) {
        User po = getUser(id);
        po.setAvatar(path);
        userMapper.updateById(po);
        return BeanMapUtil.copyPassport(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(long id, String newPassword) {
        User po = getUser(id);

        Assert.hasLength(newPassword, "密码不能为空!");

        po.setPassword(MD5.md5(newPassword));
        userMapper.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User po = getUser(id);

        Assert.hasLength(newPassword, "密码不能为空!");

        Assert.isTrue(MD5.md5(oldPassword).equals(po.getPassword()), "当前密码不正确");
        po.setPassword(MD5.md5(newPassword));
        userMapper.updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountProfile updateEmail(long id, String email) {
        User po = userMapper.selectById(id);

        if (email.equals(po.getEmail())) {
            throw new MtonsException("邮箱地址没做更改");
        }

        User check = getByEmail(email);

        if (check != null && check.getId() != po.getId()) {
            throw new MtonsException("该邮箱地址已经被使用了");
        }
        po.setEmail(email);
        userMapper.updateById(po);
        return BeanMapUtil.copyPassport(po);
    }

    @Override
    public User getByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(long id, int status) {
        User po = userMapper.selectById(id);

        po.setStatus(status);
        userMapper.updateById(po);
    }

    @Override
    public long count() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Consts.STATUS_NORMAL);
        return userMapper.selectCount(queryWrapper);
    }
}
