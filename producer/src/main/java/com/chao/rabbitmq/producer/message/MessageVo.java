package com.chao.rabbitmq.producer.message;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mq消息体
 * @param <T>
 * @author 杨文超
 * @date 2020-06-12
 */
@Component
public class MessageVo<T>{

    private String id; //消息id uuid生成 唯一标识
    private T data;     //发送数据
    private Date date;  //发送时间

    public MessageVo(){}

    public MessageVo(String id, T data, Date date) {
        this.id = id;
        this.data = data;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", date=" + date +
                '}';
    }
}
