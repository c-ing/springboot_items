package com.rabbit.api.rabbitapi.basic.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws Exception{

        // 1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        // 3. 通过connection 创建一个Channel
        Channel channel = connection.createChannel();

        // 4. 声明（创建) 一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);

        // 5. 创建消费者
      //  QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6. 设置channel
        /*channel.basicConsume(queueName, true, queueingConsumer);

        // 7. 获取消息
        while (true) {
            QueueingConsumer.Delivery deliver = queueingConsumer.nextDelivery();
            String msg = new String(deliver.getBody());
            System.err.println("消费端："+msg);
            Map<String,Object>  headers = deliver.getProperties().getHeaders();
            System.err.println("headers get my1 value： "+headers.get("my1"));
        }*/
    }
}
