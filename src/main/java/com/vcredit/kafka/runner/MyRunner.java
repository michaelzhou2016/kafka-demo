package com.vcredit.kafka.runner;

import com.vcredit.kafka.dto.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/17 17:20
 */
@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
//        kafkaTemplate.send(MessageBuilder.withPayload(42)
//                .setHeader(KafkaHeaders.TOPIC, "myTopic")
//                .build());
//        kafkaTemplate.send(MessageBuilder.withPayload("43")
//                .setHeader(KafkaHeaders.TOPIC, "myTopic")
//                .build());
//        Test test = new Test("test");
//        kafkaTemplate.send(MessageBuilder.withPayload(test)
//                .setHeader(KafkaHeaders.TOPIC, "myTopic")
//                .build());
//        Test test2 = new Test("test2");
//        kafkaTemplate.send(MessageBuilder.withPayload(test2)
//                .setHeader(KafkaHeaders.TOPIC, "myTopic")
//                .build());
        kafkaTemplate.send("myTopic", new Test("test3"));
        latch.await(60, TimeUnit.SECONDS);
    }
}
