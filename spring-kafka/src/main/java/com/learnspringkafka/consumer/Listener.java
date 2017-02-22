package com.learnspringkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by z001qgd on 12/3/16.
 */
public class Listener implements ConsumerSeekAware,AcknowledgingMessageListener {

    public final CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private boolean offSetFlag = true;

    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("Read Record is " + record);
        countDownLatch1.countDown();
    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback consumerSeekCallback) {
        System.out.println("Inside registerSeekCallback : ");
        //seek(String topic, int partition, long offset);
        //consumerSeekCallback.seek("my-topic",2,5 );
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
        System.out.println("Inside onPartitionsAssigned : ");
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
        System.out.println("Inside onIdleContainer : ");
    }

//    @KafkaListener(id = "foo", topics = "my-topic", group = "group1" )
    public void onMessage1(ConsumerRecord<?, ?> record) {

       // ConsumerRecord record = (ConsumerRecord) o;

        try{
            if(record.value().equals("12"))
                throw new Exception();
            System.out.println(" Offset : " + record.offset() + " partition : "+ record.partition() + " Message : "+ record.value());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is : " + e);
        }

    }

    @Override
    @KafkaListener(id = "foo", topics = "my-topic", group = "group1" )
    public void onMessage(Object data, Acknowledgment acknowledgment) {

        try{
            ConsumerRecord record = (ConsumerRecord) data;
            System.out.println(" Offset : " + record.offset() + " partition : "+ record.partition() + " Message : "+ record.value());
            throwEx((String)record.value());
            if(offSetFlag)
            acknowledgment.acknowledge();
        }catch(CannotCreateTransactionException e){
            offSetFlag = false;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception is : " + e);
        }
    }

    public void throwEx(String record){
        if(record.equals("12"))
            throw new CannotCreateTransactionException("Exception");
    }
}
