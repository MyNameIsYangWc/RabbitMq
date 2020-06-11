package com.chao.rabbitmq.consumer.controller;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class RabbitMQConsumerController {

//=====================================================================================
    @ApiOperation(value = "接收DirectMq消息",notes = "接收DirectMq消息")
    @ApiImplicitParams({
    })
    @RabbitListener(queues = "direct")
    public void processMsg( String msg){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Receive接收消息::"+msg);
    }

//=====================================================================================

    @ApiOperation(value = "接收topicMq消息",notes = "接收topicMq消息")
    @ApiImplicitParams({
    })
    @RabbitListener(queues = "topic")
    public void processTopicMsg(String msg){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("topic接收消息::"+msg);
    }

    @ApiOperation(value = "接收topicMq2消息",notes = "接收topicMq2消息")
    @ApiImplicitParams({
    })
    @RabbitListener(queues = "topic2")
    public void processTopicMsg2(String msg){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("topic2接收消息::"+msg);
    }
}
