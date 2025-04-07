package com.programming.techie.cqrs.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProcedureApp {

    public static void main(final String[] args) {
        // Set up properties for the Kafka producer
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.163.128:29092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Send a message to the topic
        ProducerRecord<String, String> record = new ProducerRecord<>("my_topic", "key1", "Hello Kafka12!");
        try {
            producer.send(record);
            System.out.println("Message sent to topic 'my_topic'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }
}