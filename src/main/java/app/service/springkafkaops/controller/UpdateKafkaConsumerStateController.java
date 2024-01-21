package app.service.springkafkaops.controller;

import app.service.springkafkaops.common.State;
import app.service.springkafkaops.msg.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UpdateKafkaConsumerStateController {

//    @Autowired
    private MessageListenerContainer messageListenerContainer;

    @Autowired
    private KafkaListenerEndpointRegistry registry;
    @Autowired
    private MessageSender messageSender;
    @GetMapping("/action")
    public String kafkaConsumerAction(@RequestParam("state") State state) {
        MessageListenerContainer messageListenerContainer = registry.getListenerContainer("myContainer");
        switch (state) {
            case START -> messageListenerContainer.start();
            case STOP -> messageListenerContainer.stop();
            case PAUSE -> messageListenerContainer.pause();
            case RESUME -> messageListenerContainer.resume();
        }
       return state.name();
    }

    @GetMapping("/send")
    public String sendMsg(@RequestParam("msg") String message) {
        messageSender.sendMessage("demo-topic", message);
        return "sent";
    }
}
