package com.vcredit.kafka.listener;

import com.vcredit.kafka.dto.Foo;
import com.vcredit.kafka.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Random;

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
    public void listenDefault(Foo foo, Acknowledgment acknowledgment) {
        boolean commitOffsets = false;

        while (!commitOffsets) {
            try {
                handleMessage(foo.getMsg());
                commitOffsets = true;
            } catch (CustomException e) {
                log.error("Exception caught. Not committing offset to Kafka.");
                commitOffsets = false;
            }
        }

        if (commitOffsets) {
            log.info("No exceptions, committing offsets.");
            acknowledgment.acknowledge();
        }
    }

    private void handleMessage(String message) throws CustomException {

        log.info("Busy handling message!");

        int messageLength = message.length();
        log.info("Message length: " + messageLength);

        Random random = new Random();
        int randomNumber = random.nextInt(100);

        log.info("Random number: " + randomNumber);

        if ((randomNumber % 2) != 0) {
            throw new CustomException("Odd number generated, so throwing this exception");
        }

        log.info("Even number generated, committing offsets.");
    }
}
