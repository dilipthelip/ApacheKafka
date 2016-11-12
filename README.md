# ApacheKafka

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



 
