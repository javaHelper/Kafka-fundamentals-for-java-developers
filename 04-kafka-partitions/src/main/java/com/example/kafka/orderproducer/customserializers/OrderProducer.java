package com.example.kafka.orderproducer.customserializers;

import com.example.kafka.orderproducer.customserializers.partitioners.VIPPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class OrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", OrderSerializer.class.getName());
        props.setProperty("partitioner.class", VIPPartitioner.class.getName());

        KafkaProducer<String, Order> producer = new KafkaProducer<>(props);
        Order order = new Order();
        order.setCustomerName("John");
        order.setProduct("IPhone");
        order.setQuantity(1);
        ProducerRecord<String, Order> record = new ProducerRecord<>("OrderPartitionedTopic", order.getCustomerName(), order);

        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}