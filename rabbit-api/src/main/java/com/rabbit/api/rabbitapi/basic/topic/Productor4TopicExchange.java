package com.rabbit.api.rabbitapi.basic.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Topic Exchange 生产者
 */
public class Productor4TopicExchange {

    public static void main(String[] args) throws Exception{
        // 1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        // 3.
        Channel channel = connection.createChannel();
        // 4. 声明
        String exchangeName = "test_topic_exchange";
        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete.abc";

        // 5. 发送

        String msg = "Hello World RabbitMQ 4 Topic Exchange Message....";
        channel.basicPublish(exchangeName,routingKey1,null,("user.save " + msg).getBytes());
        channel.basicPublish(exchangeName,routingKey2,null,("user.update " + msg).getBytes());
        channel.basicPublish(exchangeName,routingKey3,null,("user.delete.abc " + msg).getBytes());

        channel.close();
        connection.close();


    }
}
