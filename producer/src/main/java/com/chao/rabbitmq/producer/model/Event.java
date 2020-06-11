package com.chao.rabbitmq.producer.model;

import java.io.Serializable;

public class Event implements Serializable {
    
    private String id;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }
}
