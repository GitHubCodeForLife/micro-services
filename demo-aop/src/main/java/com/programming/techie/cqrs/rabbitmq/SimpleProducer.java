package com.programming.techie.cqrs.rabbitmq;

import com.rabbitmq.client.*;

public class SimpleProducer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // Establish a connection to RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.128"); // Set RabbitMQ server host (localhost by default)
        factory.setUsername("admin"); // Set RabbitMQ server username
        factory.setPassword("123456");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare a queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "Hello RabbitMQ!";
            AMQP.BasicProperties props  = new AMQP.BasicProperties();
            // Send a message to the queue
            channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
            System.out.println("Sent: '" + message + "'");
        }
    }
}
