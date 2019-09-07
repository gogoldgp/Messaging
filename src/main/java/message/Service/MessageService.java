package message.Service;

import message.LDM.MessageModel;
import message.LDM.MessageUser;
import message.repositories.MessageClusterGraphRepo;
import message.repositories.MessageUserGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    public String updateFriends(String sender, String receiver) {
//        StringBuilder stringBuilder = new StringBuilder();
//        MessageUser senderUser = messageUserGraphRepo.findByEmailID(sender);
//        MessageUser receiverUser = messageUserGraphRepo.findByEmailID(receiver);
//        if(senderUser!=null && receiverUser!=null){
//            senderUser.setFriends(Arrays.asList(receiverUser));
//            receiverUser.setFriends(Arrays.asList(senderUser));
//            List<MessageUser> listofFriends = new ArrayList<>();
//            listofFriends.add(senderUser);
//            listofFriends.add(receiverUser);
//            stringBuilder.append(messageUserGraphRepo.saveAll(listofFriends));
//            stringBuilder.append("\n");
//            stringBuilder.append(populateMessageMOdelAndSave(listofFriends));
//        }
//        else{
//            return "Invalid email IDs";
//        }
//        return "update friends and link generated!." + stringBuilder;
//    }

    private MessageModel populateMessageMOdelAndSave(List<MessageUser> messageUsers) {
//        String sender = messageUsers.get(0).getEmailID();
//        String receiver = messageUsers.get(1).getEmailID();
//        String id = "MSG_CLUSTER_"+sender+"_"+receiver;
//        StampedMessage samplemessageSender = new StampedMessage(id+"_"+sender,new ArrayList<>(),new ArrayList<>());
//        StampedMessage samplemessageReceiver = new StampedMessage(id+"_"+receiver,new ArrayList<>(),new ArrayList<>());
//        MessageModel model = new MessageModel(id, Arrays.asList(samplemessageReceiver,samplemessageSender),messageUsers);
//        return messageClusterGraphRepo.save(model);
//    }
//    private String timestamp(){
//       return new Timestamp(new Date().getTime()).toString();
//    }
//
//    public String sendMessage(String sender, String receiver, String message) {
//        String id;
//        Optional<MessageModel> model = messageClusterGraphRepo.findById("MSG_CLUSTER_"+sender+"_"+receiver);
//        if(model.isEmpty()){
//            model= messageClusterGraphRepo.findById("MSG_CLUSTER_"+receiver+"_"+sender);
//        }
//        if(model.isEmpty()){
//            return "Cant send message! " + receiver + " is not a mutual friend";
//        }
//        id = model.get().getMessageClusterIdentifier();
//       List<StampedMessage> stampedMessages = model.get().getMessages();
//        stampedMessages.forEach(stampedMessage -> {
//            if(StringUtils.equals(stampedMessage.getSenderId(),id + "_" + sender)){
//                stampedMessage.getMessage().add(message);
//                stampedMessage.getTimestamp().add(timestamp());
//            }
//        });
//        messageClusterGraphRepo.save(model.get());
//        return "Message: " + message + "send successfully";
        return null;
    }


}
