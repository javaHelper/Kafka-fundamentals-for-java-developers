package com.example.assignment;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TrackProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<Long, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<Long, String> record = new ProducerRecord<>("TrackTopic", 1L, "22.576N,88.3639E");
            producer.send(record, new TrackCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}