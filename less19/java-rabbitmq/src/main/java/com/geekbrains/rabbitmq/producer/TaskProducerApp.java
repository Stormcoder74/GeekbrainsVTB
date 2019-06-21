package com.geekbrains.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class TaskProducerApp {
    private static final String TASK_QUEUE_NAME = "task_queue1";
    private static final int AMOUNT_TASKS = 10;

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            boolean durable = true;
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
            for (int i = 1; i <= AMOUNT_TASKS; i++) {
                String message = "Task #" + i;
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                Thread.sleep(2000);
                System.out.println("Added new task '" + message + "'");
            }
        }
    }
}
