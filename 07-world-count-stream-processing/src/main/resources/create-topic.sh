kafka-topics --create \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic streams-wordcount-input

kafka-topics --create \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic streams-wordcount-output

kafka-console-producer --bootstrap-server localhost:9092 --topic streams-wordcount-input

kafka-console-consumer --bootstrap-server localhost:9092 \
    --topic streams-wordcount-output \
    --from-beginning \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

# Output

```
## Key : null Value : apple banana orange guava apple banana
## Key : null Value : apple apple banana
```

kafka-console-producer --bootstrap-server localhost:9092 --topic streams-wordcount-input
>apple banana orange guava apple banana
>apple apple banana
>

kafka-console-consumer --bootstrap-server localhost:9092 \
    --topic streams-wordcount-output \
    --from-beginning \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
apple	1
banana	1
orange	1
guava	1
apple	2
banana	2
apple	3
apple	4
banana	3