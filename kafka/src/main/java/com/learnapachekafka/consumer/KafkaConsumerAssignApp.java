package com.learnapachekafka.consumer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaConsumerAssignApp {

	public static void main(String[] args) {

		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092,localhost:9093 ");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test");


		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);

		TopicPartition partition=new TopicPartition("consumer-topic-1", 0);
		TopicPartition partition1=new TopicPartition("consumer-topic-2", 2);
		ArrayList<TopicPartition> partitions=new ArrayList<TopicPartition>();
		partitions.add(partition);
		partitions.add(partition1);

		consumer.assign(partitions);

		try {

			while(true){

				ConsumerRecords<String, String> records = consumer.poll(10);

				for(ConsumerRecord<String, String> record : records){

					System.out.println("Record read in KafkaConsumerAssignApp : "+ record.toString());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			consumer.close();
		}
	}
}
