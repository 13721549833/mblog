package com.mtons.mblog.modules.rabbitmq;

import com.mtons.mblog.modules.service.PostSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PostMqHandler
 * @Auther: Jerry
 * @Date: 2020/5/14 17:37
 * @Desctiption: 消息处理
 * @Version: 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstant.ES_QUEUE)
public class PostMqHandler {

    @Autowired
    private PostSearchService postSearchService;

    @RabbitHandler
    public void handler(PostMqMessage message) {
        log.info("mq 收到一条消息： {}", message.toString());
        switch (message.getAction()) {
            case PostMqMessage.CREATE_OR_UPDATE:
                postSearchService.createOrUpdateIndex(message);
                break;
            case PostMqMessage.REMOVE:
                postSearchService.removeIndex(message);
                break;
            default:
                log.error("没找到对应的消息类型，请注意！！ --》 {}", message.toString());
                break;
        }
    }
}
