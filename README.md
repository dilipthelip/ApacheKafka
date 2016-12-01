# ApacheKafka

This tutorial is created is based on the tutorial from PluralSight. This README is totally based on Windows OS.    

[KafkaMustLookLink](https://kafka.apache.org/0100/documentation.html)

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
**Windows**  
-	Run the **zookeeper-server-start.bat** file.This file looks for zookeper.propeties file.  
**Mac**  
- Run the  **./zookeeper-server-start.sh ../config/zookeeper.properties**
-	Run the follwing command **zookeeper-server-start.bat ..\\..\config\zookeeper.properties**.  
-	You will notice the below line in the command line which tells you that it had successfully started the Zookeper.  
-   [2016-11-13 08:40:23,040] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)  

### How to start a Kafka Broker?  

-	The process a very simple.
**Windows**  
Run the **kafka-server-start.bat ..\\..\config\server.properties** file.  

**Mac**  
Run the **./kafka-server-start.sh ../config/server.properties**

-	You will notice the below line in the command line window. This confirms the KAFKA sever is successfully started.  
-	[2016-11-13 08:47:08,463] INFO Registered broker 0 at path /brokers/ids/0 with addresses: PLAINTEXT -> EndPoint(2QBZP12.hq.target.com,9092,PLAINTEXT) (kafka.utils.ZkUtils  


### How to create a Topic?

**Windows**  
-	Run the following command **kafka-topics.bat --create --topic my_topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1**.

**MAC**  
-	Run the following command **./kafka-topics.sh --create --topic new_topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1**
	-	**partitions** -	Partitions is the base for Sacalability and achieve high levels of throughput. Check the **Kafka Partiotions** section below.  
	-	**replication-factor**	-	This ensures that the messages are stored redundantly across multiple brokers.  
-	You will notice the following line in the command line window.  
-	WARNING: Due to limitations in metric names, topics with a period ('.') or underscore  could collide. To avoid issues it is best to use either, but not both.  
-	Created topic "my_topic"

### How to alter a topic in a broker ?  

-	Run the following command **kafka-topics.bat --zookeeper localhost:2181 --alter --topic my-topic --partitions 4**  
-	The following command will increase the number of partitions the previous number to 4.  

### How to check the list of topics?  
-	Run the following command **kafka-topics.bat --list --zookeeper localhost:2181**.  
-	The results will display list of topics avalilable  in the cluster.  

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


### How to check the configuration of a all topics in a cluster?  
**Windows**
-	Run the following command **kafka-topics.bat --describe --zookeeper localhost:2181**  
**MAC**
-	Run the following command **./kafka-topics.sh --describe --zookeeper localhost:2181**  
-	This command will give you the below result.  

```
Topic:consumer-topic-1  PartitionCount:3        ReplicationFactor:1     Configs:
        Topic: consumer-topic-1 Partition: 0    Leader: 0       Replicas: 0     Isr: 0
        Topic: consumer-topic-1 Partition: 1    Leader: 0       Replicas: 0     Isr: 0
        Topic: consumer-topic-1 Partition: 2    Leader: 0       Replicas: 0     Isr: 0
Topic:consumer-topic-2  PartitionCount:3        ReplicationFactor:1     Configs:
        Topic: consumer-topic-2 Partition: 0    Leader: 0       Replicas: 0     Isr: 0
        Topic: consumer-topic-2 Partition: 1    Leader: 0       Replicas: 0     Isr: 0
        Topic: consumer-topic-2 Partition: 2    Leader: 0       Replicas: 0     Isr: 0
Topic:my_topic  PartitionCount:1        ReplicationFactor:1     Configs:
        Topic: my_topic Partition: 0    Leader: 0       Replicas: 0     Isr: 0
Topic:replicate_topic   PartitionCount:1        ReplicationFactor:3     Configs:
        Topic: replicate_topic  Partition: 0    Leader: 0       Replicas: 1,2,0 Isr: 0
```  

### How to instantiate a producer?  

**Windows**  
- 	Run the following command **kafka-console-producer.bat --broker-list localhost:9092 --topic my_topic**  

**Mac**  

- Run the following command  **./kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic**
- 	Once the above command is run, you can have the window open and type whatever you want.Every time you press the **Enter** key the message gets pushed to the broker.  

#### Issues ?

For the below issue, Please have this parameter set in **Server.properties**.

```
advertised.listeners=PLAINTEXT://localhost:9092
```

```
[2016-12-01 14:01:15,232] WARN Error while fetching metadata with correlation id 37 : {new_topic=LEADER_NOT_AVAILABLE} (org.apache.kafka.clients.NetworkClient)
```
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

-	When the producer is ready to send messages in to the Apache Kafka Cluster. It must have knowledge about atlease one of the broker to which it has to send the message.  
-	The metadata related data is sent back to the kafka producer which has the partition related information.  

### Kafka Consumer point of view:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka14.png)   

-	Consumer inquires zookeeper about which zookeeper owns which partition.  
-	Once it gets the information it fetches the message from the appropriate partition in the topic which sits on a specific broker in a cluster.  

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

**RecordAccumulator:**  
-	Producer now dispatches the message to a queue like data structure which is called Record Accumulator.  
-	Lot of resources are involved in order to read or publish a message in to a Topic which eventually is a overhead for the system. So resources are gracefully used in Kafka in order to maintain efficiency and high throughput.  
-	Suppose you have a garage full of boxes you need to move all of them to a new place. If the goal is efficiency meaning with less time and enerygy. What type of vehicle would you use in this case ? Car or Truck ?. The answer is Moving truck. Truck will take lesser time compare to a car. Because there is going to be less trips compared to car.  
-	Kafka uses a similar kind of approach **Micro Batching**.Same concept in the producer, consumer and Kafka end.    
-	Record Accumulator has a collection of **RecordBatch** for each **topic-partition** combination.The number of producer records that a single **Recordbatch**  can hold is based on a number of factors.  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka17.png)   

-	Each **Recordbatch** has a limit of how many producer records it can hold.It is maintained by **batch.size**.It represents the maximum number of bytes each and every **RecordBatch** can hold.  
-	There is a global threshold value **buffer.memory** which represents the total number of bytes that all the buffers can hold.  
-	If the **ProducerRecord** in **RecordBatch** reaches the **buffer.memory** value then **max.block.ms** comes in to effect.  	
-	This **max.block.ms** value represents how many millisecs that **producer.send** method will be blocked for.    
-	When the record gets accumulated in the **Recordbatch** then two things happen.  
	-	If the total buffer size reaches the batch size limit then the records are sent immediately.
	-	If the total buffer size does not reach the threshold then how long a producer record can wait in the **RecordBatch**?
		-	Another property **linger.ms** comes in to picture.This represents how long the unfilled buffer can wait ?  
		-	It checks for the below condition and sends the message to the topic.  
```
if(recordbatch.size<batch.size)  
linger.ms=1ms
```

-
![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka18.png)   

-	When the **producerRecord** is sent to the broker then a **RecordMetaData** which will have information about the message that are successfully or unsuccessfully received.  

### Delivery Guarantees:  
-	When a producer sends a record , producer can specify what level of acknowledgment it expects from the broker.This setting is called **acks**.This can be set using the Properties setting while instantiating an **KafkaProducer**.    
	- Different settings for **acks**:  
		-	0:fire and forget - Dangerous Option, No acknowledgement from Broker.  
		-	1:leader Acknowledged - Accept the acknowledgement only from the leader.It does not expect the acknowledgement from the other replica brokers.    
		-	2:replication quorum acknowledgement - Acknowledgment from the all sync replicas.This provides a higher level of assurance that the message was successfully sent and received.The performance will be really low with this settings.  
**Broker responds with error:**  
-	Have the **retries** configuration employed.  
-	**retry.backoff.m** - wait time between retries.  

### Ordering guarantees:  
-	Messages are ordered by partiotion not globally across partitions.  
-	Orders can get complicated if you have retry enabled.  
-	This can be handled **max.in.flight.request.per.connection** to 1.At any given time only one request can be sent.  

### Demo for Kafka Producer:  

Check the **KafkaProducerApp** in the workspace.  

## Kafka CONSUMER:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka19.png)  

### How to subscribe to a topic?  

**Approach 1:**  
```
Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList("replicate-topic")); // You can subscribe to any number of topics.
```

**Approach 2:**		
```
Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);
		//Alternatively you can use Regular expression.

		consumer.subscribe(Arrays.asList("rep*"));
```

### What happens when you call the subscribe method multiple times ?		 

```
consumer.subscribe(Arrays.asList("replicate-topic"));
```

```
consumer.subscribe(Arrays.asList("my-topic"));
```

The second call overrides the first subscription.  

The ideal scenario is to maintain the topics in a List.  
```
List<String> topics=new ArrayList<String>();
		topics.add("replicate-topic");
		topics.add("my-topic");

		consumer.subscribe(topics); // You can subscribe to any number of topics.
```

### How to unsubscribe to a topic?  

**Approach 1:**

You can unsubscribe using **unsubscribe** method.

```
KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);

		List<String> topics=new ArrayList<String>();
		topics.add("replicate-topic");
		topics.add("my-topic");

		consumer.subscribe(topics); // You can subscribe to any number of topics.


		consumer.unsubscribe();

```

**Approach 2:**

```
	KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);

		List<String> topics=new ArrayList<String>();
		topics.add("replicate-topic");
		topics.add("my-topic");

		consumer.subscribe(topics); // You can subscribe to any number of topics.
		topics.clear();

		consumer.subscribe(topics) // passing an empty list.  

```

### Difference between Subscribe and Assign:  

**Subscribe:**  
-	This method is for Topic suscription.  
-	You can subscribe to one topic, one to many partitions.  
-	You can subscribe to many topics, many more partitions.  

**Assign:**  
-	This method is for Partition suscription.  
-	One or more Partitions , regardless of topic.  
-	Manual , Self administering mode.  

```
KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);

		TopicPartition partition=new TopicPartition("replicate-topic", 0);
		ArrayList<TopicPartition> partitions=new ArrayList<TopicPartition>();
		partitions.add(partition);

		consumer.assign(partitions); //Remember this is not incremental.  
```  

## The Poll Loop:  

-	This is the primary function of the KAFKA consumer. This is the **heart and soul** of kafka consumer.  
-	Continiously polling for brokers for data using the poll method.  

Sample Consumer code for Subscribe:  

```
Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9091");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test");

		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);


		ArrayList<String> topics=new ArrayList<String>();
		topics.add("consumer-topic-1");
		topics.add("consumer-topic-2");

		consumer.subscribe(topics); // You can subscribe to any number of topics.

		try {

			while(true){

				ConsumerRecords<String, String> records = consumer.poll(10);

				for(ConsumerRecord<String, String> record : records){

					System.out.println("Record read in KafkaConsumerApp : " +  record.toString());
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

```


### How to test this ? How to publish bunch of messages to the Kafka Topic?  

Run the below command:  
This command will publish a bunch of messages in to the cluster.  
**kafka-producer-perf-test.bat --topic consumer-topic-1 --num-records 50 --record-size 1 --throughput 10 --producer-props bootstrap.servers=localhost:9092 key.serializer=org.apache.kafka.common.serialization.StringSerializer value.serializer=org.apache.kafka.common.serialization.StringSerializer**

If you run this command **kafka-producer-perf-test.bat** then in the console you will find details about each and every attribute listed in the console.  

## Kafka Consumer Polling:  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka20.png)  

-	When Subscribe or Assign method is invoked then the content within the Collection object is used to set the Subscriptionstate object within the consumer.  
-	This objects stands as a source of truth for the relationship between the cluster ,broker and the Kafka consumer.  
-	SubscriptionState object works well with the **ConsumerCoordinator** in managing the offset.  
-	When the **poll()** method is invoked , using the **bootstrap.servers** property the consumer invokes  the cluster to fetch the metadata using the Fetcher. This starts the communication between the consumer and broker.  
-	Fetcher communicates with the Broker using the Consumer Network client. This client sends TCP packets and the consumer sends heartbeats which enables the cluster to know what consumers are connected.  
-	Additionally the initial response for metadata is sent and recieved.The metadata will be kept updtodate whenerver the  poll method is invoked.  
-	Once the metadata is recieved then it will be sent to Consumer coordinator which in turn updates the Subscription state with the new partition assignments and offset details.  
-	The fetcher needs to know which partitions or which topics does the consumer need to pull the messages from it.Consumer gets those details from the SubscriptionState object.  
-	The arguments inside the **poll(100)** represents the number of milliseconds the network client is to spend polling the cluster for new messages.When the timeout expires a batchof records added to an in memory buffer where they are deserialized , grouped in to consumer records by topic and partitions.Once the fetcher finishes the process it returns the object for further processing.   
-	The **poll()** is a single threaded process.  
-	There might be implications that it could not be able to process the messages real time if you the consumer subscribes to many topics and partitions.  

## Consumer Offset:  

-	The **Offset** is the key parameter which each and every Kafka consumer read messages from that point in the topic.  
**Last Commiteed Offset** -  When a consumer is going to read messages from a Kafka Consumer it needs to establish **what is has?** and **what it needs to read**. This is called last committed offset.It is maintained at the partition level.Reason being each partition is mutually exclusive to each other.  
**log-end offset:** - This specifies the last message in the partition.  

#### Property parameters to maintain the **committed** offset:  

The following parameters are optional.  

1)	enable.auto.commit = true
	-	This gives Kafka the responsibitlity to manage and move the last commited offset to the current position offset.   
2)	auto.commit.interval
	-	By default it is set to 5000(ms).  
	-	If you have a process that takes more than 5000(ms) then irrespective kafka is commit that offset regardless of whether it is processed or not.  
	- 	Let us assume a scenario where something failed while processing  but the time it failed is past 5000ms then in this case it is hard to go back to the failed record because the offset is already committed by kafka.  

The **Offset** commit behavior is configurable.  
	-	By default it commits the offset after the message is read and the time is past 5seconds.  

Kafka stores the message Offset in a topic called **_consumer_offsets** with 50 partitions.  

#### How to view consumer offset ?  

**kafka-topics.bat --describe --topic __consumer_offsets --zookeeper localhost:2181**  

**ConsumerCoordinator** is responsible object for communicating to the cluster and consumer_offsets are produced in to the topic.  

Consumer Offset can be maintained either **Automatic** or **manual**.

Automatic	=	**enable.auto.commit = true(Default)**  	
Manual		=	**enable.auto.commit = false**  

### Manual Offset:  

-	Consistency control
-	In this approach you have a clear control of considering when the message is declared as done and offset can be commited.  

**commitSync**
	-	The consumer will have a precise control of when to consider a record is truly read from a broker.  
	-	These are synchronouos.  
	-	The preffered time to commit the offset is after the list of consumer records are processed in the consumer from the topic. THe reason being it blocks the processing until it receives the acknowledgement from the topic.  
	-	The good news is that it automatically retries to process until it succeeds. The retry can be set using retry.backoff.ms setting.    
	-	It might add a overall latency to the overall polling process.

**commitASync**  
	-	No Retries.  
	-	You may not know whether the commit is succedded or not.  
	-	Callback Option.With this option you can get the status of the offset and handle the response.    
	-	The throughput and over all performance is better compared to the commitSync method.  
	-	This is not a recommended way to commit the offset until you have a call back and handle the response gracefully.  

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka21.png)  

-	The place where offset management occurs is after the poll method call times out and presented the records for processing irrespctive of the auto commit call happening in the background or the explcit commit call using the commit API's.  
-	The commit takes the batch of records and determines their offsets and asks the consumer coordinator to commit them to the kafka cluster via the consumer network client.  
-	When the offsets are committed then the Subscription state object will be updated with the latest offset and fetcher will know what offsets are commited and from where it can read the message from the Kafka.  

## Scaling with Consumer Groups:  

-	A consumer group means independent consumers working as a team.  
-	**group.id** settings is needed to be set in order to work as a consumer group.  
-	Share the message consumption and processing load.  
	-	Parallelism and throughput.
	-	Redundancy.
	-	Performance.
-	Consumers with the same **group.id** will be considered as a consumer group.They all should be consuming the messages from the same topic.  
-	Behind the scenes there will be a designated broker is elected to serve as a group co-ordinator. THe role is to monitor and maintain the consumer group membership.    


![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka22.png)  

-	The group co-ordinator interacts with the cluster and zookeepr and assigns the partitions in the topic to a specific kafka Consumer.  
-	From the time the consumer group is formed it sends the hearbeat messages to the **group co-ordinator** at an interval specified in **hearbeat.interval.ms**.The group coordinator relies on this parameter and validates the consumer is alive and participates in the consumer group.  
 -	The value set **session.timeout.ms** parameter is the time the group co-ordinator waits for not recieving any heartbeat to consider the consumer failed and take necessary action.  


 **Rebalance**
 -	If there is a consumer that does not sent the hearbeat past the wait time then it does the rebalance to assign the partition to the available consumer.  
 -	The newly assigned consumer will take twice the load.If the offset management is not handled correctly then there is a very high risk for duplication.    

![](https://github.com/dilipthelip/ApacheKafka/blob/master/images/kafka23.png)  

-	If the faulty consumer joins back the consumer group then another rebalance occurs.  
-	When a new partition is added to the kafka topic then rebalance occurs.  
-	If you have a over provisioned consumers.Let us say that there are 4 consumers in the consumer group and 3 partitions. In this case the consumer group is over provisioned with one additional consumer. So in this case one additional consumer will not be assigned to read the message from Kafka.  
-	If you delete some consumers then the partition rebalance will happen. But that does not happen immediately it takes some time to perform the rebalance.

## Consumer Configuration:  

-	fetch.min.bytes 	-	Minimum number of bytes that can read from the poll method.

## Custom Serializer and DeSerializer:  

-	**Confluent** is one of the biggest Apache Kafka contributor.
-	**Apache Avro** serialization format.This is to address all the serialiazation and deserialization problems.
-	The producer can serialize in an Avro version which in turn can be deserialized using the Apache avro.  
-	Schema registry and version management can ve done in the server.
-	THis is an open source.  

## Apache Kafka Connect:  
-
