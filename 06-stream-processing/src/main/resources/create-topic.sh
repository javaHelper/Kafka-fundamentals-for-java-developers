kafka-topics --create \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic streams-dataflow-input

kafka-topics --create \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1 \
    --topic streams-dataflow-output


kafka-console-producer --bootstrap-server localhost:9092 --topic streams-dataflow-input
>Hello World
>Hello Spring Boot
>Hello Node.js
>

# Check output on sink topic

kafka-console-consumer --bootstrap-server localhost:9092 --topic streams-dataflow-output
Hello World
Hello Spring Boot
Hello Node.js

kafka-console-consumer --bootstrap-server localhost:9092 \
    --topic streams-dataflow-output \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer


# Console Output
```
kafka-console-producer --bootstrap-server localhost:9092 --topic streams-dataflow-input
>Hello World
>Hello Spring Boot
>Hello Node.js
>
```