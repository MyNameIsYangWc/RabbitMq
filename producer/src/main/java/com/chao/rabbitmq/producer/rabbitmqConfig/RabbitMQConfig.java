package com.chao.rabbitmq.producer.rabbitmqConfig;

import org.springframework.amqp.core.*;
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

    /**
     * fanout 模式 广播形式
     * @return
     */
    @Bean(name="fanoutA")
    public Queue fanoutA() {
        return new Queue("fanout.A");
    }

    /**
     * fanout 模式 广播形式 用例2
     * @return
     */
    @Bean(name="fanoutB")
    public Queue fanoutB() {
        return new Queue("fanout.B");
    }


    /**
     * fanout 模式 广播形式 用例3
     * @return
     */
    @Bean(name="fanoutC")
    public Queue fanoutC() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");//配置广播路由器
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("fanoutA") Queue fanoutA,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("fanoutB") Queue fanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutB).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier("fanoutC") Queue fanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutC).to(fanoutExchange);
    }
}
