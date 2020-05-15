package com.mtons.mblog.config;

import com.mtons.mblog.modules.rabbitmq.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit配置
 *
 * @author xiaomi
 * @date 2020/05/14
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue exQueue() {
        return new Queue(RabbitConstant.ES_QUEUE);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitConstant.ES_EXCHAGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(exQueue()).to(exchange()).with(RabbitConstant.ES_ROUTING_KEY);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(RabbitConstant.EMAIL_QUEUE);
    }

    @Bean
    DirectExchange emailExchage(){
        return new DirectExchange(RabbitConstant.EMAIL_EXCHAGE);
    }

    @Bean
    Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(emailExchage()).with(RabbitConstant.EMAIL_ROUTING_KEY);
    }
}