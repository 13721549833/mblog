package com.mtons.mblog.modules.rabbitmq;

import com.mtons.mblog.modules.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EmailMqHandler
 * @Auther: Jerry
 * @Date: 2020/5/15 9:48
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstant.EMAIL_QUEUE)
public class EmailMqHandler {

    @Autowired
    private MailService mailService;

//    @Autowired
//    private SiteOptions siteOptions;
//    private static final String EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";
//    private static final String BIND_EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";
//    private static final String FORGOT_EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";
//    private static final String REGISTER_EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";



    @RabbitHandler
    public void handler(EmailMqMessage message) {
        log.info("mq 收到一条消息： {}", message.toString());
//        String title = title = MessageFormat.format(EMAIL_TITLE, siteOptions.getValue("mail_site_name"));
//        switch (message.getAction()) {
//            case Consts.CODE_BIND:
//                title = MessageFormat.format(BIND_EMAIL_TITLE, siteOptions.getValue("mail_site_name"));
//                break;
//            case Consts.CODE_FORGOT:
//                title = MessageFormat.format(FORGOT_EMAIL_TITLE, siteOptions.getValue("mail_site_name"));
//                break;
//            case Consts.CODE_REGISTER:
//                title = MessageFormat.format(REGISTER_EMAIL_TITLE, siteOptions.getValue("mail_site_name"));
//                break;
//            default:
//                log.error("没找到对应的消息类型，请注意！！ --》 {}", message.toString());
//                break;
//        }
        mailService.sendTemplateEmail(message.getTo(), message.getTitle(), message.getTemplate(), message.getContent());
    }
}
