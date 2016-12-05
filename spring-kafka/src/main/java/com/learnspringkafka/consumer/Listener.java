package com.learnspringkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by z001qgd on 12/3/16.
 */
public class Listener {

    public final CountDownLatch countDownLatch1 = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "my-topic", group = "group1" )
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("Read Record is " + record);
        countDownLatch1.countDown();
    }
}
