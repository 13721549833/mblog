package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : landy
 * @version : 1.0
 * @date : 2019/11/6
 */
@Data
@TableName("mto_links")
public class Links {
    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private String url;

    private LocalDateTime created;

    private LocalDateTime updateTime;

}
