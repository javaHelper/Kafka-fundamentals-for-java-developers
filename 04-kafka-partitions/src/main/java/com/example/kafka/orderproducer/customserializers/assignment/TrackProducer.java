package com.example.kafka.orderproducer.customserializers.assignment;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.github.fernanda.maia.kafka.avro.Track;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TrackProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "http://localhost:9092");
        props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("schema.registry.url", "http://localhost:8081");
        props.setProperty("partitioner.class", TrackPartitioner.class.getName());

        try (KafkaProducer<Long, Track> producer = new KafkaProducer<>(props)) {
            Track track = Track.newBuilder().setId(1L).setLatitude("37.2431N").setLongitude("115.793W").build();

            ProducerRecord<Long, Track> record = new ProducerRecord<>("TrackPartitionsTopic", track.getId(), track);
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}