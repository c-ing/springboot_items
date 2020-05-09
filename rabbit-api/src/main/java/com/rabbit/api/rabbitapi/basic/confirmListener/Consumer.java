package com.rabbit.api.rabbitapi.basic.confirmListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

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

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.#";
        String queueName = "test_confirm_queue";

        //4. 声明交换机和队列然后进行绑定设置，最后绑定路由key
        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5. 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6. 设置channel
        channel.basicConsume(queueName, true, queueingConsumer);

        // 7. 获取消息
        while (true) {
            QueueingConsumer.Delivery deliver = queueingConsumer.nextDelivery();
            String msg = new String(deliver.getBody());
            System.err.println("消费端：" + msg);

        }
    }
}
