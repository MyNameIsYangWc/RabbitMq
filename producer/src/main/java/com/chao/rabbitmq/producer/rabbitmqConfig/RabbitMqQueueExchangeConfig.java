package com.chao.rabbitmq.producer.rabbitmqConfig;

import com.chao.rabbitmq.producer.Enum.ConfigEnum;
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
        return new Queue(ConfigEnum.Direct.getRoutingKey());
    }
//================================================================================================================

    /**
     * topic队列 需要绑定交换机
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name = "topic")
    public Queue topicQueue(){
        return new Queue(ConfigEnum.Topic1.getQueue());
    }

    /**
     * topic 交换机
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ConfigEnum.Topic1.getExchange());
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
        return BindingBuilder.bind(topicQueue).to(exchange).with(ConfigEnum.Topic1.getRoutingKey());
    }

    /**
     * topic 模式 用例2
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name = "topic2")
    public Queue topicQueue2(){
        return new Queue(ConfigEnum.Topic2.getQueue());
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
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(topicQueue).to(exchange).with(ConfigEnum.Topic2.getRoutingKey());
    }
//================================================================================================================

    /**
     * fanout 模式 广播形式
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name="fanoutA")
    public Queue fanoutA() {
        return new Queue(ConfigEnum.FanoutA.getQueue());
    }

    /**
     * fanout 模式 广播形式 用例2
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name="fanoutB")
    public Queue fanoutB() {
        return new Queue(ConfigEnum.FanoutB.getQueue());
    }


    /**
     * fanout 模式 广播形式 用例3
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean(name="fanoutC")
    public Queue fanoutC() {
        return new Queue(ConfigEnum.FanoutC.getQueue());
    }

    /**
     * 配置广播交换机
     * @author 杨文超
     * @Date 2020-06-12
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(ConfigEnum.FanoutA.getExchange());
    }

    /**
     * 配置广播交换机与队列绑定
     * @author 杨文超
     * @Date 2020-06-12
     */
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

//================================================================================================================
    /**
     * 延时消息队列配置
     * @author 杨文超
     * @Date 2020-06-15
     */
    @Bean
    Queue messageTtlQueue() {
        return QueueBuilder
                .durable(ConfigEnum.Delay.getQueue())
                 // 配置到期后转发的交换机
                .withArgument("x-dead-letter-exchange",ConfigEnum.Topic2.getExchange())
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key","topic.2")
                .build();
    }

    /**
     * 延时消息交换机配置
     * @author 杨文超
     * @Date 2020-06-15
     */
    @Bean
    DirectExchange  ttlexchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(ConfigEnum.Delay.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 延时队列和延时交换机的绑定-routekey
     * @param messageTtlQueue
     * @param messageTtlDirect
     * @return
     */
    @Bean
    public Binding messageTtlBinding(Queue messageTtlQueue, DirectExchange messageTtlDirect) {
        return BindingBuilder
                .bind(messageTtlQueue)
                .to(messageTtlDirect)
                .with(ConfigEnum.Delay.getRoutingKey());
    }
}
