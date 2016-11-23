package com.learnapachekafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerApp {
	
	public static void main(String[] args) {
		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092,localhost:9093");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);
		
		try {
			
			for(int i=0;i<100;i++){
				myProducer.send(new  ProducerRecord<String, String>("consumer-group-topic", "MyMessage1 " + Integer.toString(i)));
				System.out.println("Sent");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			myProducer.close();
		}
		
		
		
		
		//("my_topic",1,System.currentTimeMillis() "MyMessage1");
		//ProducerRecord<String, String> producerRecord1= new ProducerRecord<String, String>("my_topic",1,System.currentTimeMillis(), "MyMessage1", "MyMessage1" );
	}

}
