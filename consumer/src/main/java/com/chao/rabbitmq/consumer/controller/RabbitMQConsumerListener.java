package com.chao.rabbitmq.consumer.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 无消息确认的队列监听(配置文件开启手动应答模式 此类监听消息不会确认,造成消息积压,故注释掉)
 * @author 杨文超
 * @date 2020-6-13
 */
@Component
public class RabbitMQConsumerListener {

////=====================================================================================
//
//    /**
//     * 两个监听器轮训模式消费队列消息，不存在重复消费，有序消费
//     * @param msg
//     */
//    @ApiOperation(value = "接收DirectMq消息",notes = "接收DirectMq消息")
//    @RabbitListener(queues = "direct")
//    public void processMsg( String msg){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("第一个DirectReceive接收消息::"+msg);
//    }
//
//    @ApiOperation(value = "接收DirectMq消息",notes = "接收DirectMq消息")
//    @RabbitListener(queues = "direct")
//    public void processMsg1( String msg){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("第二个DirectReceive接收消息::"+msg);
//    }
////=====================================================================================
//
//    @ApiOperation(value = "接收topicMq消息",notes = "接收topicMq消息")
//    @RabbitListener(queues = "topic")
//    public void processTopicMsg(String msg){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("topic接收消息::"+msg);
//    }
//
//    @ApiOperation(value = "接收topicMq2消息",notes = "接收topicMq2消息")
//    @RabbitListener(queues = "topic2")
//    public void processTopicMsg2(String msg){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("topic2接收消息::"+msg);
//    }
////=====================================================================================
//
//    @ApiOperation(value = "接收fanoutMqA消息",notes = "接收fanoutMqA消息")
//    @RabbitListener(queues = "fanout.A")
//    public void processFanoutMsgA(String msg){
//        System.out.println("fanout.A接收消息::"+msg);
//    }
//
//    @ApiOperation(value = "接收fanoutMqB消息",notes = "接收fanoutMqB消息")
//    @RabbitListener(queues = "fanout.B")
//    public void processFanoutMsgB(String msg){
//        System.out.println("fanout.B接收消息::"+msg);
//    }
//
//    @ApiOperation(value = "接收fanoutMqC消息",notes = "接收fanoutMqC消息")
//    @RabbitListener(queues = "fanout.C")
//    public void processFanoutMsgC(String msg){
//        System.out.println("fanout.C接收消息::"+msg);
//    }
}
