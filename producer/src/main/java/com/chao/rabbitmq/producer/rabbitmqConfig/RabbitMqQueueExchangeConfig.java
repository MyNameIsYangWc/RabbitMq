package com.chao.rabbitmq.producer.rabbitmqConfig;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化:队列,交换机,与routingKey
 * @author 杨文超
 * @Date 2020-06-12
 */
@Configuration
public class RabbitMqQueueExchangeConfig {
//================================================================================================================
    /**
     * direct 模式
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    public Queue directQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // 一般设置一下队列的持久化就好,其余两个就是默认false
        //   return new Queue("TestDirectQueue",true,true,false);
        return new Queue("direct");
    }
//================================================================================================================

    /**
     * topic 模式 需要绑定交换机
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name = "topic")
    public Queue topicQueue(){
        return new Queue("topic");
    }

    /**
     * topic 交换机
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * topic绑定到交换机
     * @param topicQueue
     * @param exchange
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    Binding bindingExchangeTopic(@Qualifier("topic") Queue topicQueue, TopicExchange exchange){
        return BindingBuilder.bind(topicQueue).to(exchange).with("topic.Key");
    }

    /**
     * topic 模式 用例2
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name = "topic2")
    public Queue topicQueue2(){
        return new Queue("topic2");
    }


    /**
     * topic绑定到交换机 用例2
     * @param topicQueue
     * @param exchange
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    Binding bindingExchangeTopic2(@Qualifier("topic2") Queue topicQueue, TopicExchange exchange){
        return BindingBuilder.bind(topicQueue).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
    }
//================================================================================================================

    /**
     * fanout 模式 广播形式
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name="fanoutA")
    public Queue fanoutA() {
        return new Queue("fanout.A");
    }

    /**
     * fanout 模式 广播形式 用例2
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name="fanoutB")
    public Queue fanoutB() {
        return new Queue("fanout.B");
    }


    /**
     * fanout 模式 广播形式 用例3
     * @author 杨文超
     * @Date 2020-06-12
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
