package com.chao.rabbitmq.producer.rabbitmqConfig;

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
     * direct 模式
     * @return
     */
    @Bean
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
     * topic 交换机
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * topic绑定到交换机
     * @param topicQueue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeTopic(@Qualifier("topic") Queue topicQueue, TopicExchange exchange){
        return BindingBuilder.bind(topicQueue).to(exchange).with("topic.Key");
    }

    /**
     * topic 模式 用例2
     * @return
     */
    @Bean(name = "topic2")
    public Queue topicQueue2(){
        return new Queue("topic2");
    }


    /**
     * topic绑定到交换机 用例2
     * @param topicQueue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeTopic2(@Qualifier("topic2") Queue topicQueue, TopicExchange exchange){
        return BindingBuilder.bind(topicQueue).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
    }
//================================================================================================================
}
