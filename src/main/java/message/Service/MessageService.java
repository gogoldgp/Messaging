package message.Service;

import message.LDM.MeesageModel;
import message.LDM.MessageUser;
import message.LDM.StampedMessage;
import message.repositories.MessageClusterGraphRepo;
import message.repositories.MessageUserGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class MessageService {
    @Autowired
    MessageUserGraphRepo messageUserGraphRepo;

    @Autowired
    MessageClusterGraphRepo messageClusterGraphRepo;

    public String saveUserToDB(MessageUser messageUser){
        if(messageUserGraphRepo.findByEmailID(messageUser.getEmailID())!=null){
            return "Duplicate Account";
        }
        messageUserGraphRepo.save(messageUser);
        return messageUser.toString();
    }

    public String updateFriends(String sender, String receiver) {
        StringBuilder stringBuilder = new StringBuilder();
        MessageUser senderUser = messageUserGraphRepo.findByEmailID(sender);
        MessageUser receiverUser = messageUserGraphRepo.findByEmailID(receiver);
        if(senderUser!=null && receiverUser!=null){
            senderUser.setFriends(Arrays.asList(receiverUser));
            receiverUser.setFriends(Arrays.asList(senderUser));
            List<MessageUser> listofFriends = new ArrayList<>();
            listofFriends.add(senderUser);
            listofFriends.add(receiverUser);
            stringBuilder.append(messageUserGraphRepo.saveAll(listofFriends));
            stringBuilder.append("\n");
            stringBuilder.append(populateMessageMOdelAndSave(listofFriends));
        }
        else{
            return "Invalid email IDs";
        }
        return "update friends and link generated!." + stringBuilder;
    }

    private MeesageModel populateMessageMOdelAndSave(List<MessageUser> messageUsers) {
        String id = "MSG_CLUSTER_"+messageUsers.get(0).getEmailID()+"_"+messageUsers.get(1).getEmailID();
        StampedMessage message = new StampedMessage("shramana","arkajit:Hello",timestamp());
        StampedMessage message2 = new StampedMessage("arkajit","arkajit:Hello",timestamp());
        MeesageModel model = new MeesageModel(id, Arrays.asList(message,message2),messageUsers);
        return messageClusterGraphRepo.save(model);
    }
    private String timestamp(){
       return new Timestamp(new Date().getTime()).toString();
    }

}
