package com.chao.rabbitmq.producer.controller;

import com.chao.rabbitmq.producer.result.Result;
import com.chao.rabbitmq.producer.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmqProducer")
@Api(value = "RabbitMQProducerController",description = "rabbitMQ生产者")
public class RabbitMQProducerController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate template;

    @ApiOperation(value = "发送信息接口",notes = "发送信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "信息",required = true,dataType = "String",paramType = "query"),
    })
    @GetMapping("/sendMQ")
    public Result sendMQ(@RequestParam String msg){

        template.convertAndSend("direct",msg);
        logger.info("发送mq消息成功s:msg"+msg);
        return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
    }
}
