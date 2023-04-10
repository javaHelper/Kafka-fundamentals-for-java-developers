package com.example.kafka.orderproducer.customserializers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "OrderGroup");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderPartitionedTopic"));

        ConsumerRecords<String, Order> orders = consumer.poll(Duration.ofSeconds(20));
        for (ConsumerRecord<String, Order> record : orders) {
            System.out.println("Customer Name : "+ record.key());
            System.out.println("Product Name :" + record.value().getProduct());
            System.out.println("Quantity :" + record.value().getQuantity());
            System.out.println("Partition :"+ record.partition());
        }
        consumer.close();
    }
}