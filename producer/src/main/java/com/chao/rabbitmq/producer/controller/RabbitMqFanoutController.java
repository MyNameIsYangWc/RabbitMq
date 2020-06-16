package com.chao.rabbitmq.producer.controller;

import com.alibaba.fastjson.JSON;
import com.chao.rabbitmq.producer.Enum.ConfigEnum;
import com.chao.rabbitmq.producer.message.MessageVo;
import com.chao.rabbitmq.producer.result.Result;
import com.chao.rabbitmq.producer.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *  广播模式 有消息发送确认机制
 * @author 杨文超
 * @Date 2020-06-12
 */
@RestController
@RequestMapping("/rabbitmqFanoutProducer")
@Api(value = "RabbitMqFanoutController",description = "Fanout发送消息")
public class RabbitMqFanoutController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Qualifier("rabbit")
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private MessageVo messageVo;

    /**
     * fanout 模式 广播模式
     * @param msg
     * @author 杨文超
     * @Date 2020-06-12
     */
    @ApiOperation(value = "Fanout发送MQ信息",notes = "Fanout发送MQ信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "信息",required = true,dataType = "Object",paramType = "body"),

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping("/FanoutSendMQ")
    public Result FanoutSendMQ(@RequestBody Object msg){

        messageVo.setId(String.valueOf(UUID.randomUUID()));
        messageVo.setData(msg);
        messageVo.setCrateDate(sdf.format(new Date()));

        template.convertAndSend(ConfigEnum.FanoutA.getExchange(),ConfigEnum.FanoutA.getRoutingKey(), JSON.toJSONString(messageVo));
        logger.info("Fanout 模式发送mq消息成功:msg::"+JSON.toJSONString(messageVo));
        return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
    }
}
