package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图片
 *
 * @author saxing 2019/4/3 21:24
 */
@Data
@TableName("mto_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 5375532923109475054L;

    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 签名
     */
    private String md5;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 引用次数
     */
    private long amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
