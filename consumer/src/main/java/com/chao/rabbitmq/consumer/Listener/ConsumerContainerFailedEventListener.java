package com.chao.rabbitmq.consumer.Listener;

import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * 客户端异常处理重启监听
 * @author 杨文超
 * @date 2020-6-15
 */
@Component
public class ConsumerContainerFailedEventListener implements ApplicationListener<ListenerContainerConsumerFailedEvent> {

    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent event) {
        if (event.isFatal()) {
//            log.error(String.format("Stopping container from aborted consumer. Reason::%s.",
//                    event.getReason()), event.getThrowable());
            SimpleMessageListenerContainer container = (SimpleMessageListenerContainer) event.getSource();
            String queueNames = Arrays.toString(container.getQueueNames());
            // 重启
            try {
                System.out.println("客户端监听异常");
                restart(container);
//                log.info("重启队列%s的监听成功！", queueNames);
            } catch (Exception e) {
//                log.error(String.format("重启队列%s的监听失败！", queueNames), e);
            }

            // TODO 告警，包含队列信息，监听断开原因，断开时异常信息，重启是否成功等...

        }
    }

    /**
     * 重启监听
     * @param container
     * @return
     */
    private void restart(SimpleMessageListenerContainer container) {
        // 暂停30s
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
//            log.error(e.getMessage());
        }

        Assert.state(!container.isRunning(), String.format("监听容器%s正在运行！", container));
        container.start();
    }
}
