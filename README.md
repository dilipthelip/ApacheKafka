# ApacheKafka

This tutorial is created is based on the tutorial from PluralSight. This README is totally based on Windows OS.    

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
- Group Membership  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka6.png)  

### Download Apache Kafka:

Download Apache Kafka from the below link. Download the latest one from the Binary downloads section. It was **Scala 2.11  - kafka_2.11-0.10.1.0.tgz** when this readMe was created.  

[Download](https://kafka.apache.org/downloads.html)

## Apache kafka Topic:
-	Kafka topic is a named feed or category of messages.  
-	An Apache Kafka topic can expand to multiple clusters for the benefit of scalability.  
-		

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

-	Kafka retains all published messages regardless of consumption.  
-	Retention period is cofigurable.  
		-	By default the retention period is 7 days or 168 hours.  
-	The retention period is set on a per topic basis.  
-	Within a cluster you can have different topics configured with different retention periods.  
-	Physical storage resources can constrain message retention.  
	
## Starting Apache Kafka and Producing and Consuming Messages:  

### How to start Zookeeper?

-	Navigate to the bin/windows directory.  
-	Run the **zookeeper-server-start.bat** file.This file looks for zookeper.propeties file.  
-	Run the follwing command **zookeeper-server-start.bat ..\\..\config\zookeeper.properties**.  
-	You will notice the below line in the command line which tells you that it had successfully started the Zookeper.  
-   [2016-11-13 08:40:23,040] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)  

### How to start a Kafka Broker?  

-	The process a very simple. Run the **kafka-server-start.bat ..\\..\config\server.properties** file.  
-	You will notice the below line in the command line window. This confirms the KAFKA sever is successfully started.  
-	[2016-11-13 08:47:08,463] INFO Registered broker 0 at path /brokers/ids/0 with addresses: PLAINTEXT -> EndPoint(2QBZP12.hq.target.com,9092,PLAINTEXT) (kafka.utils.ZkUtils  


### How to create a Topic?  
	
-	Run the following command **kafka-topics.bat --create --topic my_topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1**. 
	-	**partitions**	-	Partitions is the base for Sacalability and achieve high levels of throughput. Check the **Kafka Partiotions** section below.  
	-	**replication-factor**	-	
-	You will notice the following line in the command line window.  
-	WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.  
-	Created topic "my_topic".  

### How to check the list of topics?  
-	Run the following command **kafka-topics.bat --list --zookeeper localhost:2181**.  
-	The results will display **my_topic**.  

### How to instantiate a producer?  
- 	Run the following command **kafka-console-producer.bat --broker-list localhost:9092 --topic my_topic**  
- 	Once the above command is run, you can have the window open and type whatever you want.After each enter the message gets pushed to the broker.  

### How to instantiate a Consumer?  

-	Run the following command **kafka-console-consumer.bat --zookeeper localhost:2181 --topic my_topic --from-beginning**.  
-	Start typing the messge in the producer window and those messages will be rendered in the console window.    

## Kafka Partitions: (LOG)   
-	Each Topic has one or more partitions.  
-	The number of partitions per topic is entirely configurable.  
-	A partition is the basis for which Kafka can:
	-	Scale.  
	-	Become fault-tolerant.  
	-	Achieve higher levels of throughput.  
-	Each partion is maintained on at least one or more brokers.  
-	Here is the create topic command **kafka-topics.bat --create --topic my_topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1**.  
-	At a minimum each topic has to have a single Partition. Partition is the physical representation of a commit log stored on one or more brokers.  
-	If you want to have more messages to be processed parallely then we need to have more partitions.    
-	You might be wondering how the Kafka partions splits the messages? It can be based on the partitioning scheme that is established by the producer.If no mechanism is mentioned then it uses Round-Robin fashion.      
-	Each and every partion is mutually exclusive.  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka11.png)  

## Overall Picture of Apache Kafka:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka12.png)   

-	When a create topic command is issued, ZooKeeper which maintains the metadata about the brokers in the cluster receives the request and checks for available brokers and assigns a leader for each partition.  
-	 When the assignment is made then each  kafka broker assigns a log for the newly assigned partion. The hosting directory will be named as per the topic+partition number.  
-	Each individual broker maintains of the subset of the metadata created by zookeeper. Metadata includes mapping of what partions are managed by what brokers?  
-	Status is sent by each broker to zookeeper.  

### Kafka Producer point of view:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka13.png)   

-	When the producer is ready to send messages in to the Apache Kafka Cluster. It must have knowledge abbout atlease one of the broker to which it has to send the message.  
-	The metadata related data is sent back to the kafka producer which has the partition related information.  

### Kafka Consumer point of view:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka14.png)   

-	Consumer inquires zookeeper about which zookeeper owns which partition.  
-	Once it gets the information it fetches the message from the appropriate partion in the topic which sits on a specific broker in a cluster.  

#### Disadvantages of more partitions:

-	The more partiotions the greater the zookeeper overhead.  
-	Message ordering can become complex.  
	-	Single partion for global ordering.  
	-	Consumer handling for ordering.  
-	The more partions the longer the leader fail-over time.  

### Fault Tolerance:  

-	Broker Failure  
-	Network issue  
-	Disk Failure  

-	If one of the broker fails, then the zookeeper knows about it and assigns the request to the available broker.  
-	The producers and consumers will be updated with the latest metadata.But the previous messages that were lost in the previous partion will not be accessible.  

### Replication Factor:  
-	This makes sure there is going to be reliable work distribution.  
-	This ensures that the messages are stored redundantly across multiple brokers.  
-	Cluster resilency  
- 	Fault Tolerance  
**Guarantees:**  
-	It is recommended to keep the value 2 or 3.  
-	N-1 broker failure tolerance.  
-	Its configured per topic basis.  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka15.png)   

As per the above image the replication-factor value is set as 3 then three copies of the topic will be created. The ISR(In Sync Replica) value is reported throughout the cluster.If the ISR = rep Factor the topic  in each partition within the topic is in a healthy state.  

## Producing Messages with Apache Kafka?  

Add the below dependency.  

`<dependency>`
`<groupId>org.apache.kafka</groupId>`
`<artifactId>kafka-clients</artifactId>`
`<version>0.10.0.1</version>`
`</dependency>`


![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka16.png)   



