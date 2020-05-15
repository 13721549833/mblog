package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章标签映射表
 * @author : langhsu
 */
@Data
@TableName("mto_post_tag")
public class PostTag {
    @TableId(type = IdType.AUTO)
    private long id;

    private long postId;

    private long tagId;

    private long weight;

}
