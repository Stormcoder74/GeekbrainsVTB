package com.geekbrains.teryaevs;

import com.rabbitmq.client.*;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("localhost");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("dexc", "direct", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "dexc", "woo");

        channel.basicPublish("dexc", "woo", null, "Java".getBytes());

        channel.basicConsume(queueName, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
