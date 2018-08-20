package com.vcredit.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/17 17:08
 */
@Slf4j
@Component
@KafkaListener(id = "multi", topics = "myTopic")
public class Listener {
    @KafkaHandler
    public void listen(String foo, Acknowledgment acknowledgment) {
        log.info("String:" + foo);
        acknowledgment.acknowledge();
    }

    @KafkaHandler
    public void listen(Integer bar, Acknowledgment acknowledgment) {
        log.info("Integer:" + bar);
        acknowledgment.acknowledge();
    }

    @KafkaHandler(isDefault = true)
    public void listenDefault(Object object, Acknowledgment acknowledgment) {
        log.info("isDefault:" + object);
        acknowledgment.acknowledge();
    }
}
