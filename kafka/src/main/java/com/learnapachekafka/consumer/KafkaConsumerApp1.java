package com.learnapachekafka.consumer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerApp1 {

	public static void main(String[] args) {

		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092,localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test");
		
		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);
		
		
		ArrayList<String> topics=new ArrayList<String>();
		topics.add("consumer-group-topic");
		//topics.add("consumer-topic-2");
		
		consumer.subscribe(topics); // You can subscribe to any number of topics.
		
		try {
			
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(10);
				
				for(ConsumerRecord<String, String> record : records){
					
					System.out.println("Record read in KafkaConsumerApp1 : " +  record.toString());
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Inside exception loop : ");
			e.printStackTrace();
		}finally{
			consumer.close();
		}
	
	}
}
