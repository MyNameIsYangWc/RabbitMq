package com.chao.rabbitmq.consumer.rabbitmqConfig;

import com.chao.rabbitmq.consumer.message.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 接收消息确认机制配置
 */
@Configuration
public class RabbitMessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MyAckReceiver myAckReceiver;//消息接收处理类

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        container.setConcurrentConsumers(10);
        container.setMaxConcurrentConsumers(10);
        //AcknowledgeMode.NONE 自动确认  AcknowledgeMode.MANUAL 手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置一个队列
        container.setQueueNames("topic");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        // container.setQueues(new Queue("TestDirectQueue",true));
        // container.addQueues(new Queue("TestDirectQueue2",true));
        // container.addQueues(new Queue("TestDirectQueue3",true));
         container.setMessageListener(myAckReceiver);
         return container;
    }

}
