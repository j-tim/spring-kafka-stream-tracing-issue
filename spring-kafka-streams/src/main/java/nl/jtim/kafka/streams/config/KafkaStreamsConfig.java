package nl.jtim.kafka.streams.config;

import lombok.extern.slf4j.Slf4j;
import nl.jtim.spring.kafka.stock.quote.avro.StockQuote;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.KafkaStreamBrancher;

import static nl.jtim.kafka.streams.config.KafkaTopicsConfiguration.STOCK_QUOTES_TOPIC_NAME;

@Slf4j
@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {
    @Bean
    public KStream<String, StockQuote> kStream(StreamsBuilder streamsBuilder) {

        return new KafkaStreamBrancher<String, StockQuote>()
                .branch((key, value) -> value.getExchange().equalsIgnoreCase("NYSE"), kStream -> kStream.to("stock-quotes-exchange-nyse"))
                .branch((key, value) -> value.getExchange().equalsIgnoreCase("NASDAQ"), kStream -> kStream.to("stock-quotes-exchange-nasdaq"))
                .branch((key, value) -> value.getExchange().equalsIgnoreCase("AMS"), kStream -> kStream.to("stock-quotes-exchange-ams"))
                .defaultBranch(kStream -> kStream.to("stock-quotes-exchange-other"))
                .onTopOf(streamsBuilder.stream(STOCK_QUOTES_TOPIC_NAME));

    }
}
