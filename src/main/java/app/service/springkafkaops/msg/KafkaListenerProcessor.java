package app.service.springkafkaops.msg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class KafkaListenerProcessor {

    @KafkaListener(id = "myContainer", topics = "${kafka.topic:demo-topic}", autoStartup = "true")
    void listen(@Payload String payload, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        log.info(payload);
        log.info("headers partition {}, topic {}, timestamp {}", partition, topic, ts);
    }
}
