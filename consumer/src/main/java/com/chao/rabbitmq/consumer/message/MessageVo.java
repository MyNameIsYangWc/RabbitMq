package com.chao.rabbitmq.consumer.message;

/**
 * mq消息体
 * @param <T>
 * @author 杨文超
 * @date 2020-06-13
 */
public class MessageVo<T>{

    private String id; //消息id uuid生成 唯一标识
    private T data;     //发送数据
    private String crateDate;  //发送时间

    public MessageVo(){}

    public MessageVo(String id, T data, String crateDate) {
        this.id = id;
        this.data = data;
        this.crateDate = crateDate;
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

    public String getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(String crateDate) {
        this.crateDate = crateDate;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", crateDate='" + crateDate + '\'' +
                '}';
    }
}
