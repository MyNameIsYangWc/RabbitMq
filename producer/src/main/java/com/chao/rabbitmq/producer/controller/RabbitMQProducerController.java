package com.chao.rabbitmq.producer.controller;

import com.chao.rabbitmq.producer.model.Event;
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

import java.util.UUID;

@RestController
@RequestMapping("/rabbitmqProducer")
@Api(value = "RabbitMQProducerController",description = "rabbitMQ生产者")
public class RabbitMQProducerController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate template;

    /**
     * Direct 模式
     * @param msg
     * @return
     */
    @ApiOperation(value = "Direct发送MQ信息",notes = "Direct发送MQ信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "信息",required = true,dataType = "String",paramType = "query"),
    })
    @GetMapping("/directSendMQ")
    public Result directSendMQ(@RequestParam String msg){

        template.convertAndSend("direct",msg);
        logger.info("Direct 模式发送mq消息成功:msg::"+msg);
        return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
    }

//===========================================================================================================================
    /**
     * topic模式
     * @param msg
     * @return
     */
    @ApiOperation(value = "Topic发送MQ信息",notes = "Topic发送MQ信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "信息",required = true,dataType = "String",paramType = "query"),
    })
    @GetMapping("/topicSendMQ")
    public Result topicSendMQ(@RequestParam String msg){

        template.convertAndSend("topicExchange","topic.Key",msg);
        logger.info("topic 模式发送mq消息成功:msg::"+msg);
        return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
    }

//===========================================================================================================================
    /**
     * fanout 模式 广播模式
     * @param msg
     * @return
     */
    @ApiOperation(value = "Fanout发送MQ信息",notes = "Fanout发送MQ信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "信息",required = true,dataType = "String",paramType = "query"),
    })
    @GetMapping("/FanoutSendMQ")
    public Result FanoutSendMQ(@RequestParam String msg){

        template.convertAndSend("fanoutExchange","",msg);
        logger.info("topic 模式发送mq消息成功:msg::"+msg);
        return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
    }
}
