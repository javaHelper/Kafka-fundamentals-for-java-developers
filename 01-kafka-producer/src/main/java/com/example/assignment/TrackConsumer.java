package com.example.assignment;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TrackConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("group.id", "TrackGroup");

        KafkaConsumer<Long, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("TrackTopic"));

        ConsumerRecords<Long, String> records = consumer.poll(Duration.ofSeconds(20));
        records.forEach(tracking -> {
            List<String> coordinates = Arrays.asList(tracking.value().split(","));
            System.out.println("Truck ID: " + tracking.key());
            System.out.println("Latitude: " + coordinates.get(0));
            System.out.println("Longitude: " + coordinates.get(1));
        });
        consumer.close();
    }
}