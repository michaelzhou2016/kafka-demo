package com.vcredit.kafka.runner;

import com.vcredit.kafka.dto.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/17 17:20
 */
@Slf4j
@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("myTopic", new Foo(1, "default"))
                .addCallback((sendResult) -> {
                            log.info(sendResult.toString());
                            countDownLatch.countDown();
                        },
                        (throwable) -> {
                            log.error("error", throwable);
                            countDownLatch.countDown();
                        });

        kafkaTemplate.send("myTopic", 1)
                .addCallback((sendResult) -> {
                            log.info(sendResult.toString());
                            countDownLatch.countDown();
                        },
                        (throwable) -> {
                            log.error("error", throwable);
                            countDownLatch.countDown();
                        });

        kafkaTemplate.send("myTopic", "demo")
                .addCallback((sendResult) -> {
                            log.info(sendResult.toString());
                            countDownLatch.countDown();
                        },
                        (throwable) -> {
                            log.error("error", throwable);
                            countDownLatch.countDown();
                        });

        countDownLatch.await(5, TimeUnit.SECONDS);
    }
}
