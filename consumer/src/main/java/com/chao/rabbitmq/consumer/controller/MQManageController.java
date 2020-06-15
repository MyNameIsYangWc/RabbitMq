package com.chao.rabbitmq.consumer.controller;

import com.chao.rabbitmq.consumer.config.MQClientMonitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MQ管理控制器
 * @author 杨文超
 * @date 2020-6-15
 */
@RestController
@RequestMapping("/mqManage")
@Api(value = "MQManageController",description = "MQ管理控制器")
public class MQManageController {

    @Autowired(required = false)
    private MQClientMonitor mqClientMonitor;

    /**
     * 重置指定队列消费者数量
     * @param queueName
     * @param concurrentConsumers
     * @return
     */
    @ApiOperation(value = "重置指定队列消费者数量",notes = "重置指定队列消费者数量")
    @GetMapping("/resetConcurrentConsumers")
    public boolean resetConcurrentConsumers(String queueName, int concurrentConsumers) {
        return mqClientMonitor.resetQueueConcurrentConsumers(queueName, concurrentConsumers);
    }

    /**
     * 重启对消息队列的监听
     * @param queueName
     * @return
     */
    @ApiOperation(value = "重启对消息队列的监听",notes = "重启对消息队列的监听")
    @GetMapping("/restartMessageListener")
    public boolean restartMessageListener(String queueName) {
        return mqClientMonitor.restartMessageListener(queueName);
    }

    /**
     * 停止对消息队列的监听
     * @param queueName
     * @return
     */
    @ApiOperation(value = "停止对消息队列的监听",notes = "停止对消息队列的监听")
    @GetMapping("stopMessageListener")
    public boolean stopMessageListener(String queueName) {
        return mqClientMonitor.stopMessageListener(queueName);
    }

    /**
     * 获取所有消息队列对应的消费者
     * @return
     */
    @ApiOperation(value = "统计所有消息队列详情",notes = "统计所有消息队列详情")
    @GetMapping("statAllMessageQueueDetail")
    public List<MQClientMonitor.MessageQueueDatail> statAllMessageQueueDetail() {
        return mqClientMonitor.statAllMessageQueueDetail();
    }
}
