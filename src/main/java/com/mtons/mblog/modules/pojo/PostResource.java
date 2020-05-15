package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章图片
 *
 * @author saxing 2019/4/3 22:39
 */
@Data
@TableName("mto_post_resource")
public class PostResource implements Serializable {

    private static final long serialVersionUID = 1679014631424444209L;

    @TableId(type = IdType.AUTO)
    private long id;

    private long postId;

    private long resourceId;

    private String path;

    private int sort;

}
