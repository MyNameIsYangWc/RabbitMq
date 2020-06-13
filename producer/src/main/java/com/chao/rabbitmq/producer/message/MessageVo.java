package com.chao.rabbitmq.producer.message;

import org.springframework.stereotype.Component;

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
    private String crateDate;  //发送时间
    private boolean flag = false; //是否重入队列

    public MessageVo(){}

    public MessageVo(String id, T data, String crateDate,boolean flag) {
        this.id = id;
        this.data = data;
        this.crateDate = crateDate;
        this.flag=false;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public String getDate() {
        return crateDate;
    }

    public void setDate(String crateDate) {
        this.crateDate = crateDate;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", date=" + crateDate +
                '}';
    }
}
