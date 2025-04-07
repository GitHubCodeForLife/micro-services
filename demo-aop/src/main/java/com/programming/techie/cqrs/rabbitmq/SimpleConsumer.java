package com.programming.techie.cqrs.rabbitmq;
import com.rabbitmq.client.*;

public class SimpleConsumer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // Establish a connection to RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.128"); // Set RabbitMQ server host
        factory.setUsername("admin"); // Set RabbitMQ server username
        factory.setPassword("123456");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
//            while (true) {
                // Declare a queue (consumer needs the same queue declaration as producer)
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                System.out.println("Waiting for messages...");

                // Define a callback for message delivery
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("Received: '" + message + "'");
                };

                // Start consuming messages from the queue
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });
//            }
        }
    }
}
