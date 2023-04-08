package com.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        System.out.println("Partition : " + metadata.partition());
        System.out.println("Offset : " + metadata.offset());
        System.out.println("Message sent successfully..");

        if (exception != null) {
            exception.printStackTrace();
        }
    }
}