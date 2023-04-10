package com.example.kafka.orderproducer.customserializers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private String customerName;
    private String product;
    private int quantity;
}