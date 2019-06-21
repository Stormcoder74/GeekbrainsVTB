package com.geekbrains.teryaevs;

import com.rabbitmq.client.*;

public class ProduserApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("localhost");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("dexc", "direct", true);
        String queueName = "Geek_DZ_19";
        channel.queueDeclare(queueName, false, false, true, null);
        channel.queueBind(queueName, "dexc", "woo");

        for (int i = 1; i <= 30; i++) {
            String task = "Task #" + i;
            channel.basicPublish("dexc", "woo", null, task.getBytes());
            System.out.println("Added task: " + task);
            Thread.sleep(3000);
        }
    }
}
