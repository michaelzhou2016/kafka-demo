package com.vcredit.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/17 18:33
 */
@Configuration
public class KafkaConfig {
    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }
}
