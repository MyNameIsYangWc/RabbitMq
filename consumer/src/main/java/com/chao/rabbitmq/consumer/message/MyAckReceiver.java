package com.chao.rabbitmq.consumer.message;

import org.springframework.stereotype.Component;

@Component
public class MyAckReceiver<T> {
    private String event;
    private T data;

    public MyAckReceiver(){}

    public  MyAckReceiver(String event,T data){
        this.event=event;
        this.data=data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
