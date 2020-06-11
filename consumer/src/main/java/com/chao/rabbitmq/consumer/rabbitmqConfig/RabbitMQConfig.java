package com.chao.rabbitmq.consumer.rabbitmqConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue directQueue() {
        return new Queue("direct");
    }

}
