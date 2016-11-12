# ApacheKafka

This tutorial is created is based on the tutorial from PluralSight.  

## What is Apache Kafka ?

A high throughput distributed messaging system.  
Addresses shortcomings of traditional data movement tools and approaches.

Apache Kafka is a **Publish/Subscribe** messaging system.  

Publishers are called **producers**.    
Suscribers are called **consumers**.  

### Topic:
**Topics** is the place in Apache Kafka where all the messages will be send and read from.  

 ![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka1.png)


### Broker:
**Broker** is the place in Apache Kafka which will carry all the topics. Broker is nothing but a server.  

Broker is a software process or referred to as an executable which runs on a machine(Physical machine or Virtual machine). Broker has access to the resources on the machine such as a file system which it uses to store messages which it catrgorizes as topics

 ![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka2.png)  

 ![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka3.png)  
 
 You can scale the number of brokers without affecting the current broker and consumer architecture.  
 Kafka cluster is a grouping of multiple kafka brokers.  
 
**Distrubuted Systems :**  
 
 A system is a collection of resources that are instructed to achieve a specific goal or function.  
 
A distributed system is one that consists of multiple independent workers or nodes.  

The system of nodes require coordination to ensure consistence and progress towards a common goal.Each node communicates with each other thorough messages.  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka4.png)  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka5.png)  


## ApacheZookeper:  

It is a centralized service for maintianing metadata about a cluster of distributed nodes.  
- Configuration information.  
- Health Stats  
- Froup Membership  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka6.png)  

### Download Apache Kafka:

Download Apache Kafka from the below link. Download the latest one from the Binary downloads section. It was **Scala 2.11  - kafka_2.11-0.10.1.0.tgz** when this readMe was created.  

[Download](https://kafka.apache.org/downloads.html)

## Apache kafka Topic:

An Apache Kafka topic can expand to multiple clusters.

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka7.png)  

### Flow of messages in to topic from Producer:

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka8.png)  

### Message Content:  

Kafka topic stores a time ordered sequence of messages that share the same category.  

Each Message has the following:  

The timestamp and Referencable identifier helps to identify the message.  

- TimeStamp
- Referencable identifier : The message recieved gets a unique identifier
	- A combination of timestamp and Referencable identifer form its placement in the sequence of messages recieved within the topic.  
- Payload (binary) : This is the actual data that is sent by the producer.

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka9.png)  

### Kafka Consumers:  

How does the different consumers maintaining their autonomy ?  

-	It mainitains it using **Message Offset**.
-	The offset is like a bookmark that maintains the last read position.  
-	The offset is entirely established and maintained by a KAFKA consumer.   
-	Since the consumer is responsible for reading the messages and processing on its own.  
-	The offset itself corresponds to the message identifier. This is the same Mesage Identifier as like the Reference identifier.  
-	The **CONSUMER** decides what message it wants to consume.  
	-	Consumer can read from the beginning.  
	-	The Offset gets increased after the consumer started to consume messages.   
				
![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka10.png)  

-	Whenever there is a new message arrives the TOPIC then connected consumers will be connected to it.  
-	Once the last message is read in the TOPIC then the consumer sets it offset and maintains it.  
	
### Message Retention policy:  

-	All Kafka retains all published messages regardless of consumption.  
-	Retention period is cofigurable.  
		-	By default the retention period is 7 days or 168 hours.  
			
-	The retention period is set on a per topic basis.  
-	Within a cluster you can have different topics configured with different retention periods.  
-	Physical storage resources can constrain message retention.  
	
	
	
		
	







 
