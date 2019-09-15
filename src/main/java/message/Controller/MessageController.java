package message.Controller;

import message.Service.ComponentService;
import message.Service.MessageService;
import message.Utils.JsonUtils;
import message.jpa.LDM.MessageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    ComponentService componentService;

    @PostMapping("/saveUserModel")
    public MessageUser saveUser(@RequestBody String json) throws IOException {
        MessageUser model ;
        try {
            model = JsonUtils.convertStringToObject(json, MessageUser.class);
        } catch (IOException e) {
            return new MessageUser(Arrays.asList("Invalid JSON provided!"));
        }
        model = componentService.save(model);
        return model;

    }
    @GetMapping("/saveUser")
    public String saveUser(@RequestParam(value = "email") String email , @RequestParam(value="username") String username){
        MessageUser messageUser = new MessageUser();
        messageUser.setEmailID(email);
        messageUser.setUsername(username);
        return messageService.saveUserToDB(messageUser);
    }
//    @GetMapping("/addFriend")
//    public String addFriend(@RequestParam(value = "sender") String sender,@RequestParam(value = "receiver") String receiver ) {
//        return messageService.updateFriends(sender,receiver);
//    }
    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "sender") String sender,@RequestParam(value = "receiver")
            String receiver, @RequestParam(value = "message") String message){

//        return messageService.sendMessage(sender,receiver,message);
        return null;
    }


}
