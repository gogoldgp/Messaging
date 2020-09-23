package message.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @KafkaListener(topics = "${cloudkarafka.topic}")
    public void processMessage(String message){
        System.out.println("Message consumed--->" + message + "\n");
    }
}
