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

        AMQP.Queue.DeclareOk response = channel.queueDeclarePassive("direct-queue");
        response.getMessageCount();
        response.getConsumerCount();

        channel.exchangeDeclare("direct-exchange-name", "direct", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind("direct-queue", "direct-exchange", "woo");

        channel.basicPublish("direct-exchange", "woo", null, "Hello".getBytes());

        channel.basicConsume("direct-queue", false, "myConsumerTag", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                channel.basicAck(deliveryTag, false);
                System.out.println(new String(body));
            }
        });
    }
}
