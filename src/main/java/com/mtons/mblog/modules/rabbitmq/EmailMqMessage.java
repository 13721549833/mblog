package com.mtons.mblog.modules.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: EmailMqMessage
 * @Auther: Jerry
 * @Date: 2020/5/15 9:50
 * @Desctiption: 邮件相关消息队列
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class EmailMqMessage implements Serializable {
    private static final long serialVersionUID = -2881079867759864292L;

    private String to;
    private String title;
    private String template;
    private Map<String, Object> content;
}
