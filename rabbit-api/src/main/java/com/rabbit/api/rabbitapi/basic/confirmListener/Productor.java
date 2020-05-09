package com.rabbit.api.rabbitapi.basic.confirmListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

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

        // 4.指定我们的消息投递模式： 消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routeingKey = "confirm.save";

        // 5. 通过Channel 发送数据
        String msg = "Hello RabbitMQ Send confirm message !";
        channel.basicPublish(exchangeName,routeingKey,null,msg.getBytes());

        // 6 添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.err.println("--------------no ack!-----------------");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.err.println("----------ack !------------------");
            }
        });


    }
}
