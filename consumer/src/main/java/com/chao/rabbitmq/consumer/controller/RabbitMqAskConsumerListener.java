package com.chao.rabbitmq.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.chao.rabbitmq.consumer.message.MessageVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;


/**
 * 接收消息并确认机制需要实现ChannelAwareMessageListener
 * @author 杨文超
 * @date 2020-6-13
 */
@Component
public class RabbitMqAskConsumerListener implements ChannelAwareMessageListener {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 监听器 开了三个线程,有序读取queue
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = {"direct","topic","topic2","fanout.A","fanout.B","fanout.C"})
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        MessageVo messageVo=null;
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            String[] msgArray = msg.split("'");
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            messageVo = JSONObject.parseObject(msgArray[1].trim(), MessageVo.class);
            logger.info(String.format("MyAckReceiver  messageId:%S  messageData:%S  createTime:%S",messageVo.getId(),messageVo.getData(),messageVo.getCrateDate()));
            logger.info(String.format("Exchange:%S || queue:%S || RoutingKey:%S",message.getMessageProperties().getReceivedExchange(),message.getMessageProperties().getConsumerQueue(),message.getMessageProperties().getReceivedRoutingKey()));
            int i=1/0;
            channel.basicAck(deliveryTag, true); //确认消息
        } catch (Exception e) {
            if(messageVo ==null ){
                //消息体异常,直接抛弃
                channel.basicReject(deliveryTag, false);
                logger.error(String.format("异常信息:%S",e));
                return;
            }
//            if(!messageVo.isFlag()){
//                //重新放回队列,此操作必须在确认消息前
//                channel.basicReject(deliveryTag, true);
//            }else{
//                //已入队列一次,抛弃队列数据,存入数据库
//                channel.basicReject(deliveryTag, false);
//                //todo 入库操作
//            }
        }
    }
}
