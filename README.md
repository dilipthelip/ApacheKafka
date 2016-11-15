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

### How to check the configuration of a topic?  
-	Run the following command **kafka-topics.bat --describe --topic replicate_topic --zookeeper localhost:2181**
-	This command will give you the below result.  
```
Topic:replicate_topic   PartitionCount:1        ReplicationFactor:3     Configs:
        Topic: replicate_topic  Partition: 0    Leader: 1       Replicas: 1,2,0 Isr: 1,2,0
```

Leader : 1 ->	This conveys that the node 1 is the leader.  
Replicas: 1,2,0 -> This conveys there is a replica in node 1,2 and 0.  
ISR(in Sync Replica) ->	Since the ISR== Replica we can say that the partition and everything in healthy state.  


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
		-	Direct  
		-	Roung-Robin  
		-	key mod -hash(MurMurhashing) 
		-	custom	
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

### How to run Multiple KafkaBroker in Local?  
-	Navigate to the **config** folder inside Kafka distribution.  
-	Create Server-0, Server-1...Server-n.properties.**n** represents the number of brokers.  
-	Change the broker.id, port, log.dirs to an unique value inside each broker.  
```
Eg., 
**Server-0.properties**  
broker.id=1
port=9092
log.dir=/tmp/kafka-logs-1

**Server-1.properties**  
broker.id=2
port=9093
log.dir=/tmp/kafka-logs-2

**Server-2.properties**  
broker.id=3
port=9094
log.dir=/tmp/kafka-logs-3

```

## Producing Messages with Apache Kafka?  

Add the below dependency.  

```<dependency> 
<groupId>org.apache.kafka</groupId>
<artifactId>kafka-clients</artifactId>
<version>0.10.0.1</version>
</dependency>
```
![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka16.png)   

### Creating a KAFKA producer:  

-	Mandatory property values for the KAFKA producer.  
```
Properties properties=new Properties();
		properties.put("bootstrap.servers", "BROKER-1:9092, BROKER-1:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
```

-	Kafka producer does not connect to all the mentioned brokers. It connects to one of the available brokers in the list.It is recommended to provide more than one broker. This is to make sure if the one of the broker mentioned is not available then it connects to the other one. 
**Key and Value Serializers:**  
-	Message content is encoded as binary. This is to optimize the size of the messages. Not only for network transmission and also for storage and compression. Since producers is the beginning of the message life cycle it is responbile for describing how the message contents are to be encoded.So that the consumer can know how to decode them.  
-	If you notice above we have used StringSerializer. This is one of the mostly used type in Kafka.  

-	There are many ways to instantiate a Kafka Producer.Below is a common way of declaring a KAFKA producer.  
`KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);'  
- 	The implemenation of Kafka producer represents a ProducerConfig class. Using the properties sent as an argument the instance of ProducerConfig class will be instantiated.  
-	From this instantiated producerConfig object the Key and value serializer will be intialized.Setting the internal values of the producer to expect the key and value of type 	String.   
- 	This is a type safe contract between the Kafka producer and the messages specification it is configured to produce.This contract extends to the consumer who reads the message from the Kafka topic need to know the message type contract to successfully decode the message from the Kafka Topic.  
-	Kafka Producer sends **ProducerRecord**.  
-	ProducerRecord is the class which holds the payload that will be published to the Kafka.  
-	ProducerRecord requires only two values Topic and Value. The optional parameters are partition,Timestamp and Key.  
	-	Topic	(Mandatory)  
	-	Value	(Mandatory)  
	-	Partition  
	-	TimeStamp  
	-	Key  

### Approach 1:
```
ProducerRecord<String, String> producerRecord= new ProducerRecord<String, String>("my_topic", "MyMessage1");  
```

### Approach 2:  
```
ProducerRecord<String, String> producerRecord1= new ProducerRecord<String, String>("my_topic",1,System.currentTimeMillis(), "key", "MyMessage1" );
```

1 - refers to the partion in the Topic.  
System.currentTimeMillis() -	This is not a required value and this gets appended to the message and adding an additional overhead of 8 bytes to the message.  
-	Kafka producer instances can only send ProducerRecords that match the key and value serializer types they are configured with.  
-	If the serializer type does not match then it will through some run time exception(SerializationException) and nothing will be sent to the Kafka cluster.  

There is a log timestamp setting available in the server.properties file.  

```
log.message.timestamp.type =[Createtime, LogAppendTime]
```
Createtime - then the producer set timestamp is used.  
LogAppendTime - The producer set timestamp is overriden by the broker set timestamp when the message is appended to the commit log.  

key - You can define the partition to which the producer need to publish the message to.  
```
KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);
ProducerRecord<String, String> producerRecord= new ProducerRecord<String, String>("my_topic", "MyMessage1");
myProducer.send(producerRecord);
```  
**Sequence of steps after the send call:**  
-	When the producer calls the send method , the producer reaches out to the cluster using the bootstrap.servers property to get the metadata. Containing detailed information about the topic, partitions and their managing brokers on the cluster.     
-	This metadata object is used to instantiate a metadata object in the producer.Throughout the producer life cycle this metadata information is maintained with the latest data.  
**Serialization:**
-	Now the producer have the producerrecord object,pass the message in to the serializer using the configured serializer.  
**Partitioner:**  
-	Next comes the partitioner, the task here is to determine to which partition in the cluster should the message be sent to.  
**Partitioner Check 1:**  
-	The Kafka Producer checks for a partition value in the producer object. If **yes** then it checks whether the partion is a valid one using the metadata returned by the cluster.
	-	If the partition is not valid then there will be some exception(InvalidArgumentException) which will abort the send 		operation. 
	-	If the partition is valid then the message will be sent to the broker using **direct** strategy.  
**Partitioner Check 2:**  
-	If the partition is not mentioned then the producer checks whether any "key" reffered in the producer.  
	-	If there is no key mentioned then round-robin strategy will be used to push the message in to the broker.  
	-	If there is a key provided then whether a non default partitioner class is provided instantiate a Kakfa producer.
		-	For this the producer class checks in the producerconfig object and looks for a specific value PARTIOTIONER_CLASS_CONFIG.This refers to the **partitioner.class** in the properties object that was passed to create the kafka producer object.  
		-	If there is no value mentioned then the producerconfig refers to the default partitioner class.Default partitioner uses a murmurhash and applies a modular function for the number of topics and thats how it determines which partition to use in the topic.  
		-	If there is a user defined Custom Partitioner class then it uses custom based partition scheme to decide on which partition that the message needs to be sent to. Add the class to the classpath and assign the new class against the **partitioner.class** property.  
		

		
		
	

	
