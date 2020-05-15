package com.mtons.mblog.modules.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.mtons.mblog.modules.pojo.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaomi
 * @ClassName: UserVo
 * @Auther: Jerry
 * @Date: 2020/4/4 10:15
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 3457018807876286669L;
    private long id;
    private String username;

    @JSONField(serialize = false)
    private String password;
    private String avatar;
    private String name;

    @JSONField(serialize = false)
    private String email;

    private int posts; // 文章数
    private int comments; // 发布评论数

    private Date created;
    private Date lastLogin;
    private String signature; // 个性签名

    private int status;

    @JSONField(serialize = false)
    private List<Role> roles = new ArrayList<>();
}
