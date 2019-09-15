package message.Service;

import message.jpa.LDM.MessageModel;
import message.jpa.LDM.MessageUser;
import message.Validator.UserValidator;
import message.jpa.repositories.MessageClusterGraphRepo;
import message.jpa.repositories.MessageUserGraphRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;

@Component
public class UserSaveService {
    @Autowired
    MessageUserGraphRepo messageUserGraphRepo;
    @Autowired
    MessageClusterGraphRepo messageClusterGraphRepo;

    public MessageUser createNewUser(MessageUser model) {
        messageUserGraphRepo.save(model);
        return model;
    }

    public MessageUser updateExistingUser(MessageUser model) {
       messageUserGraphRepo.modifyUsername(model.getUsername(),model.getEmailID(),model.getUpdatedTimeStamp());
       if(!CollectionUtils.isEmpty(model.getFriends())){
           for (MessageModel messageModel : model.getMessageModelList()) {
               createRelationshipsToSenderAndReceiverNode(createNodesForMessageModelAndStampedMessages(messageModel),messageModel.getSenderId(),messageModel.getReceiverId());
           }
       }
       return model;
    }

    private void createRelationshipsToSenderAndReceiverNode(String nodesForMessageModelAndStampedMessages, String senderId, String receiverId) {
        messageClusterGraphRepo.updateRelationships(nodesForMessageModelAndStampedMessages,senderId,receiverId);
    }

    private String createNodesForMessageModelAndStampedMessages(MessageModel messageModel){
        MessageModel model = messageClusterGraphRepo.save(messageModel);
        return model.getMessageClusterIdentifier();
    }

}
