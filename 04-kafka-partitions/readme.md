#

````
kafka-topics --create --topic OrderPartitionedTopic --bootstrap-server localhost:9092 --partitions 10 replication-factor 10
Created topic OrderPartitionedTopic.
````

````
kafka-topics --describe --topic OrderPartitionedTopic --bootstrap-server localhost:9092
Topic: OrderPartitionedTopic	TopicId: wQz4zimwSg-EoBGa-ODpWA	PartitionCount: 10	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: OrderPartitionedTopic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 1	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 2	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 3	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 4	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 5	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 6	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 7	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 8	Leader: 0	Replicas: 0	Isr: 0	Offline: 
	Topic: OrderPartitionedTopic	Partition: 9	Leader: 0	Replicas: 0	Isr: 0	Offline: 
````