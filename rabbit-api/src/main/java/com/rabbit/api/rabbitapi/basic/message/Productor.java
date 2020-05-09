package com.rabbit.api.rabbitapi.basic.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Productor {

    public static void main(String[] args) throws Exception {
        // 1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        // 3. 通过connection 创建一个Channel
        Channel channel = connection.createChannel();

        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", 111);
        headers.put("my2", 222);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2) //持久化的投递
                .contentEncoding("UTF-8")
                .expiration("20000") // 毫秒级
                .headers(headers) // 自定义属性
                .build();

        // 4. 通过Channel 发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ!";
            // 1.exchange, 2.routeKey
            channel.basicPublish("","test001",properties,msg.getBytes());
        }

        // 5. 记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}
