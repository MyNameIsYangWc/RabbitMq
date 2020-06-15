package com.chao.rabbitmq.producer.Enum;

/**
 * 定义队列<>换机
 * @author 杨文超
 * @Date 2020-06-15
 */
public enum ConfigEnum {

    //简单模式
    Direct("directQueue"),

    //topic模式
    Topic1("topicQueue","topicExchange","topic.Key"),
    Topic2("topicQueue2","topicExchange","topic.#"),

    //fanout模式
    FanoutA("fanoutQueue.A","fanoutExchange"),
    FanoutB("fanoutQueue.B","fanoutExchange"),
    FanoutC("fanoutQueue.C","fanoutExchange"),

    //延时模式
    Delay("DelayQueue","delayExchange","delayKey",10*1000);

    private String queue;
    private String exchange;
    private String routingKey;
    private int delayTime;


    ConfigEnum(String routingKey) {
        this.routingKey = routingKey;
    }

    ConfigEnum(String queue, String exchange) {
        this.queue = queue;
        this.exchange = exchange;
    }

    ConfigEnum(String queue, String exchange, String routingKey) {
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    ConfigEnum(String queue, String exchange, String routingKey, int delayTime) {
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.delayTime = delayTime;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }}
