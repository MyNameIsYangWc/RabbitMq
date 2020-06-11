package com.chao.rabbitmq.consumer.controller;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class RabbitMQConsumerController {


    @ApiOperation(value = "接收mq消息",notes = "接收mq消息")
    @ApiImplicitParams({
    })
    @RabbitListener(queues = "direct")
    public void processMsg( String msg){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Receive:"+msg);
    }
}
