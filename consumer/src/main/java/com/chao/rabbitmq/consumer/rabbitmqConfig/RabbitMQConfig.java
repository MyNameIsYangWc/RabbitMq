package com.chao.rabbitmq.consumer.rabbitmqConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

//================================================================================================================
    /**
     * direct 模式不需要帮绑定交换机
     * @return
     */
    @Bean(name = "direct")
    public Queue directQueue() {
        return new Queue("direct");
    }
//================================================================================================================

    /**
     * topic 模式 需要绑定交换机
     * @return
     */
    @Bean(name = "topic")
    public Queue topicQueue(){
        return new Queue("topic");
    }

    /**
     * topic 模式 需要绑定交换机
     * @return
     */
    @Bean(name = "topic2")
    public Queue topicQueue2(){
        return new Queue("topic2");
    }




}
