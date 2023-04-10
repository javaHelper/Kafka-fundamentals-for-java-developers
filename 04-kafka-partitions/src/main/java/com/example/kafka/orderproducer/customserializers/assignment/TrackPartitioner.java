package com.example.kafka.orderproducer.customserializers.assignment;

import io.github.fernanda.maia.kafka.avro.Track;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class TrackPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Track record = (Track) value;
        int partitionNumber = 5;

        if (record.getLongitude() != "115.793W" || record.getLatitude() != "37.2431N") {
            List<PartitionInfo> infoList = cluster.availablePartitionsForTopic("TrackPartitionsTopic");
            partitionNumber = Math.abs(Utils.murmur2(keyBytes) % infoList.size() - 1);
        }
        return partitionNumber;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}