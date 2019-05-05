package message.services;

import message.LDM.MessageUser;
import message.repositories.MessageUserGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

@Service
public class MessageService {
    private MessageUserGraphRepo messageUserGraphRepo;

    public MessageService(MessageUserGraphRepo messageUserGraphRepo) {
        this.messageUserGraphRepo = messageUserGraphRepo;
    }

    @Transactional
    public void saveUser(MessageUser messageUser){
           if(messageUser!=null && !StringUtils.isEmpty(messageUser.getEmailID())
                   && !StringUtils.isEmpty(messageUser.getPassword()) ) {
               messageUserGraphRepo.save(messageUser);
           }
    }




}
