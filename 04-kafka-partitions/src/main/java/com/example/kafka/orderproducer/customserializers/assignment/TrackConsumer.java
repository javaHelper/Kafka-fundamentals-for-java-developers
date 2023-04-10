package com.example.kafka.orderproducer.customserializers.assignment;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.github.fernanda.maia.kafka.avro.Track;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TrackConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "http://localhost:9092");
        props.setProperty("key.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("schema.registry.url", "http://localhost:8081");
        props.setProperty("group.id", "TrackGroup");
        props.setProperty("specific.avro.reader", "true");

        try (KafkaConsumer<Long, Track> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("TrackAvroTopic", "TrackPartitionsTopic"));
            while (true) {
                ConsumerRecords<Long, Track> records = consumer.poll(Duration.ofSeconds(20));
                records.forEach(c -> {
                    Track coordinates = c.value();
                    System.out.println("\nID: " + c.key());
                    System.out.println("LATITUDE: " + coordinates.getLatitude());
                    System.out.println("LONGITUDE: " + coordinates.getLongitude());
                    System.out.println("PARTITION: " + c.partition());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}