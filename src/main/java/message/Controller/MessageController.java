package message.Controller;

import message.Service.ComponentService;
import message.Service.MessageService;
import message.Utils.JsonUtils;
import message.jpa.LDM.MessageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    ComponentService componentService;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final String TOPIC = "ctz7ie8k-default";

    @PostMapping("/saveUserModel")
    public MessageUser saveUser(@RequestBody String json) throws IOException {
        MessageUser model ;
        try {
            model = JsonUtils.convertStringToObject(json, MessageUser.class);
        } catch (IOException e) {
            return new MessageUser(Collections.singletonList("Invalid JSON provided!"));
        }
        model = componentService.save(model);
        return model;
    }
    @GetMapping("/confirmFriend")
    public boolean confirmFriend(@RequestParam("senderID") String senderID, @RequestParam("receiverID") String receiverID){
        return componentService.addFriend(senderID,receiverID);
    }

    @GetMapping("/send")
    public String send(@RequestParam("message") String message){
      kafkaTemplate.send(TOPIC,"My message :" + message);
      return "Successfully sent message->" + message;
    }
}
