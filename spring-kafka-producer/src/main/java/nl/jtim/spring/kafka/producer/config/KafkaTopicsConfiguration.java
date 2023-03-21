package nl.jtim.spring.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfiguration {

    public final static String STOCK_QUOTES_TOPIC_NAME = "stock-quotes";

    @Bean
    public NewTopic stockQuotesTopic() {
        return TopicBuilder.name(STOCK_QUOTES_TOPIC_NAME)
                .build();
    }
}
