package com.learnapachekafka.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaConsumerApp {
	
	public static void main(String[] args) {
		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);
		
		TopicPartition partition=new TopicPartition("replicate-topic", 0);
		ArrayList<TopicPartition> partitions=new ArrayList<TopicPartition>();
		partitions.add(partition);
		
		consumer.assign(partitions);
		
		/*
		List<String> topics=new ArrayList<String>();
		topics.add("replicate-topic");
		topics.add("my-topic");
		
		consumer.subscribe(topics); // You can subscribe to any number of topics.
		topics.clear();
		
		consumer.subscribe(topics);*/
		
		/*//Alternatively you can use Regular expression.
		
		consumer.subscribe(Arrays.asList("rep*"));*/
	}

}
