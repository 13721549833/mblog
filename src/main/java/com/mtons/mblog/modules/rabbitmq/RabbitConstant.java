package com.mtons.mblog.modules.rabbitmq;

/**
 * @ClassName: RabbitConstant
 * @Auther: Jerry
 * @Date: 2020/5/15 9:23
 * @Desctiption: rabbit常量
 * @Version: 1.0
 */
public class RabbitConstant {
    /**
     * 邮件队列
     */
    public static final String EMAIL_QUEUE = "email_queue";
    public static final String EMAIL_EXCHAGE = "email_exchage";
    public static final String EMAIL_ROUTING_KEY = "email_routing_key";
    /**
     * 短信队列
     */
    public static final String MESSAGE_QUEUE = "message_queue";
    public static final String MESSAGE_EXCHAGE = "message_exchage";
    public static final String MESSAGE_ROUTING_KEY = "message_routing_key";
    /**elasticsearch队列*/
    public final static String ES_QUEUE = "es_queue";
    public final static String ES_EXCHAGE = "es_exchage";
    public final static String ES_ROUTING_KEY = "es_routing_key";
}
