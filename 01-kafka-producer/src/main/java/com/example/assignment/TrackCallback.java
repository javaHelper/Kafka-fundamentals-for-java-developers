package com.example.assignment;

import org.apache.kafka.clients.producer.RecordMetadata;

public class TrackCallback implements org.apache.kafka.clients.producer.Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        System.out.println(metadata.partition());
        System.out.println("Message sent successfully!");
    }
}