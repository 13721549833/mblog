package com.mtons.mblog.modules.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PostMqMessage
 * @Auther: Jerry
 * @Date: 2020/5/14 16:58
 * @Desctiption: 文章相关消息队列
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class PostMqMessage implements Serializable {
    private static final long serialVersionUID = 3572599349158869479L;
    /**
     * 新增或修改
     */
    public final static String CREATE_OR_UPDATE = "create_or_update";
    /**
     * 删除
     */
    public final static String REMOVE = "remove";
    /**
     * 文章id
     */
    private long postId;
    /**
     * 文章操作类型
     */
    private String action;
}
