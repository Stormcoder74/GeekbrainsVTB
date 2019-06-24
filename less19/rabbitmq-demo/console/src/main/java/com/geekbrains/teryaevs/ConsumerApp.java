package com.geekbrains.teryaevs;

import com.rabbitmq.client.*;

public class ConsumerApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("localhost");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        String queueName = "Geek_DZ_19";
        channel.queueDeclare(queueName, false, false, true, null);

        channel.basicConsume(queueName, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("Recieved new task! To do it?");
                System.out.println("Yes, do it.");
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body) + " done!");
            }
        });
    }
}
