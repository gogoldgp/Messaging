package message;

import message.LDM.MessageUser;
import message.services.MessageService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
//@RequestMapping(path = "/NeoMessaging")
public class MessageController {
//    @Autowired
//    MessageService messageService;
//    @GetMapping("/saveForSignUp/{email}")///{password}")
//    public void saveForSignUp(@PathVariable(name = "email") String email)//, @PathVariable String password)
//     {
//        MessageUser user = new MessageUser();
//        user.setEmailID(email);
////        user.setPassword(password);
//        messageService.saveUser(user);
//    }
    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET)
    @ResponseBody
    public void test(@PathVariable("id") Long id, BindingResult bindingResult){
        System.out.println(id + "TEST WORKING!"+ bindingResult );
    }


}
