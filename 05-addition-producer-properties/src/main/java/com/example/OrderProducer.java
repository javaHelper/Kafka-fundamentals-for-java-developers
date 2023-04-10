package com.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "343434334");
        props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        props.setProperty(ProducerConfig.RETRIES_CONFIG, "2");
        props.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "400");
        props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10243434343");
        props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "500");
        //props.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "2000000");
        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);
        ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Mac Book Pro", 10);

        try {
            producer.send(record,new OrderCallback());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}