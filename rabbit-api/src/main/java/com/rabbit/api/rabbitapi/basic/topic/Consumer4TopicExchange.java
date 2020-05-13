package com.rabbit.api.rabbitapi.basic.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * topic exchange 消费者
 */
public class Consumer4TopicExchange {

    public static final Logger logger = LoggerFactory.getLogger(Consumer4TopicExchange.class);

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

        Channel channel = connection.createChannel();

        // 4.声明
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
         String routingKey = "user.*";
        //String routingKey = "user.#";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // durable 是否持久化消息
       /* QueueingConsumer consumer = new QueueingConsumer(channel);
        // 参数：队列名称，是否自动ack,consumer
        channel.basicConsume(queueName,true,consumer);

        // 循环获取消息
        while(true){
            // 获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            logger.info("收到消息：{}",msg);
        }*/


    }
}
