package com.programming.techie.cqrs.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConsumerApp {
    public static void main(String[] args) {
        // Set up properties for the Kafka consumer
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.163.128:29092");
        properties.put("group.id", "test-group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        // Create the consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("my_topic"));
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        // Read messages
        try {
            while (true) {
                consumer.poll(1000).forEach(record -> {
                    System.out.println("Consumed message: " + record.value());
                });
            }
        } finally {
            consumer.close();
        }


    }

}
