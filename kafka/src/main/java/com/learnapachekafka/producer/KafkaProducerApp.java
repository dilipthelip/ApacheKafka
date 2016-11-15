package com.learnapachekafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerApp {
	
	public static void main(String[] args) {
		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "BROKER-1:9092, BROKER-1:9093");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);
		ProducerRecord<String, String> producerRecord= new ProducerRecord<String, String>("my_topic", "MyMessage1");
		
		myProducer.send(producerRecord);
		
		//("my_topic",1,System.currentTimeMillis() "MyMessage1");
				ProducerRecord<String, String> producerRecord1= new ProducerRecord<String, String>("my_topic",1,System.currentTimeMillis(), "MyMessage1", "MyMessage1" );
	}

}
