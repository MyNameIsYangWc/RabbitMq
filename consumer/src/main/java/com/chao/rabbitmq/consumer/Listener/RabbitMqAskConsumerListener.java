package com.chao.rabbitmq.consumer.Listener;

import com.alibaba.fastjson.JSONObject;
import com.chao.rabbitmq.consumer.message.MessageVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 接收消息并确认机制需要实现ChannelAwareMessageListener
 * @author 杨文超
 * @date 2020-6-13
 */
@Component
public class RabbitMqAskConsumerListener implements ChannelAwareMessageListener {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 监听器 开了三个线程,有序读取queue
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = {"directQueue","topicQueue","topicQueue2","fanoutQueue.A","fanoutQueue.B","fanoutQueue.C"})
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
            logger.info(String.format("MyAckReceiver  messageId:%s  messageData:%s  createTime:%s",messageVo.getId(),messageVo.getData(),messageVo.getCrateDate()));
            logger.info(String.format("Exchange:%s || queue:%s || RoutingKey:%s",message.getMessageProperties().getReceivedExchange(),message.getMessageProperties().getConsumerQueue(),message.getMessageProperties().getReceivedRoutingKey()));
            //todo 业务逻辑处理
            handleBusiness(messageVo);
            channel.basicAck(deliveryTag, true); //确认消息
        } catch (Exception e) {
            if(messageVo ==null ){
                //消息体异常,直接抛弃
                channel.basicReject(deliveryTag, false);
                logger.error(String.format("异常信息:%s",e));
                return;
            }
            if(!redisTemplate.hasKey(messageVo.getId())){
                //存入redis失效时间30天
                redisTemplate.opsForValue().set(messageVo.getId(),messageVo.getCrateDate(),30, TimeUnit.DAYS);
                //重新放回队列,此操作必须在确认消息前
                channel.basicReject(deliveryTag, true);
                logger.warn(String.format("数据重新入队列，原因：%s",e));
            }else if(!redisTemplate.opsForValue().get(messageVo.getId()).equals(messageVo.getCrateDate())){
                //存入redis失效时间30天
                redisTemplate.opsForValue().set(messageVo.getId(),messageVo.getCrateDate(),30, TimeUnit.DAYS);
                //重新放回队列,此操作必须在确认消息前
                channel.basicReject(deliveryTag, true);
                logger.warn(String.format("数据重新入队列，原因：%s",e));
            }else{
                //已入队列一次,抛弃队列数据,存入数据库
                channel.basicReject(deliveryTag, false);
                logger.warn(String.format("数据消费失败,原因：%s,存入MongoDB",e));
                mongoTemplate.save(messageVo);
            }
        }
    }

    //处理业务逻辑
    public void handleBusiness(MessageVo messageVo){
        logger.info(String.format("业务逻辑处理"));
    }
}
