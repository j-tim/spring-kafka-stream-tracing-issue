package nl.jtim.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import nl.jtim.spring.kafka.stock.quote.avro.StockQuote;

@Component
@Slf4j
public class StockQuoteConsumer {

    public final static String STOCK_QUOTES_TOPIC_NAME = "stock-quotes";

    @KafkaListener(topics = {"stock-quotes-exchange-nyse", "stock-quotes-exchange-nasdaq", "stock-quotes-exchange-ams"})
    public void on(StockQuote stockQuote, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition) {
        log.info("Consumed from partition: {} value: {}", partition, stockQuote);
    }
}
