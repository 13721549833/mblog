package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色映射表
 *
 * @author - langhsu on 2018/2/11
 */
@Data
@TableName("shiro_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 8313153002286973185L;

	@TableId(type = IdType.AUTO)
    private Long id;

	private Long userId;

    private Long roleId;

}
