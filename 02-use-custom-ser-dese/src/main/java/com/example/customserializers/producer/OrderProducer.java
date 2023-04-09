package com.example.customserializers.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class.getName());

        KafkaProducer<String, Order> producer = new KafkaProducer<>(props);
        Order order = new Order();
        order.setCustomerName("John");
        order.setProduct("IPhone");
        order.setQuantity(1);
        ProducerRecord<String, Order> record = new ProducerRecord<>("OrderTopic", order.getCustomerName(), order);

        try {
            producer.send(record);
            System.out.println("Record sent successfully is : " + record.value());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}