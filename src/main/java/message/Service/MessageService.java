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
        MeesageModel model = new MeesageModel(id, null,messageUsers);
        return messageClusterGraphRepo.save(model);
    }
    private String timestamp(){
       return new Timestamp(new Date().getTime()).toString();
    }

    public String sendMessage(String sender, String receiver, String message) {
        StampedMessage newMessage = new StampedMessage(sender,message,timestamp());
        Optional<MeesageModel> model1 = messageClusterGraphRepo.findById("MSG_CLUSTER_"+sender+"_"+receiver);
        Optional<MeesageModel> model2 = messageClusterGraphRepo.findById("MSG_CLUSTER_"+receiver+"_"+sender);
        if(!model1.isPresent() && !model2.isPresent()){
            return "Cant send message! " + receiver + " is not a mutual friend";
        }
        if(model1.isPresent()){
            if( model1.get().getMessages()==null){
               model1.get().setMessages(Collections.singletonList(newMessage));
            }
            else {
                model1.get().getMessages().add(newMessage);
            }
            messageClusterGraphRepo.save(model1.get());
        }
        else{
            if( model2.get().getMessages()==null){
                model2.get().setMessages(Collections.singletonList(newMessage));
            }
            else {
                model2.get().getMessages().add(newMessage);
            }
            messageClusterGraphRepo.save(model2.get());
        }
        return "Message: " + message + "send successfully";
    }
}
