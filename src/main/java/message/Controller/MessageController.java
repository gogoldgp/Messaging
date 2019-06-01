package message.Controller;

import message.LDM.MessageUser;
import message.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @GetMapping("/test")
    public void test(){
        System.out.println("Test Success");
    }
    @GetMapping("/saveUser")
    public String saveUser(@RequestParam(value = "email") String email , @RequestParam(value="username") String username){
        MessageUser messageUser = new MessageUser();
        messageUser.setEmailID(email);
        messageUser.setUsername(username);
        return messageService.saveUserToDB(messageUser);

    }
    @GetMapping("/addFriend")
    public String addFriend(@RequestParam(value = "sender") String sender,@RequestParam(value = "receiver") String receiver ) {
        return messageService.updateFriends(sender,receiver);
    }


}
