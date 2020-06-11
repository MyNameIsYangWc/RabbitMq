package com.chao.rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class,args);
        System.out.println("RabbitMQ启动成功！！！");
    }
}
