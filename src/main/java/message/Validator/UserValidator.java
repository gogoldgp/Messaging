package message.Validator;

import message.jpa.LDM.MessageCargo;
import message.jpa.LDM.MessageModel;
import message.jpa.LDM.MessageUser;
import message.jpa.LDM.StampedMessage;
import message.jpa.repositories.MessageUserGraphRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class UserValidator {
    @Autowired
    MessageUserGraphRepo messageUserGraphRepo;

    public void validateForUserCreate(MessageCargo cargo){
        MessageUser processedUser = cargo.getProcessedRequest();
        List<String> errors = new ArrayList<>();
        processedUser.setFriends(new ArrayList<>());
        validateCreateFields(processedUser,errors);
        processedUser.setErrors(errors);
        populateTimeStamp(processedUser,cargo);
    }

    private void populateTimeStamp(MessageUser processedUser,MessageCargo cargo) {
        processedUser.setCreatedTimeStamp(cargo.getCurrentTimeStamp());
        processedUser.setUpdatedTimeStamp(cargo.getCurrentTimeStamp());
    }

    private void validateCreateFields(MessageUser processedUser, List<String> errors) {
        if(StringUtils.isBlank(processedUser.getEmailID()) || StringUtils.isBlank(processedUser.getPassword())){
            errors.add("Both emailID and password is mandatory to create User");
        }
        else if(messageUserGraphRepo.findByEmailID(processedUser.getEmailID())!=null){
           errors.add("Account with emailID " + processedUser.getEmailID() + " already exists");
        }
        if(!CollectionUtils.isEmpty(processedUser.getFriends())){
            errors.add("Cannot add friends while user is being created");
        }
    }

    public void validateForUserUpdate(MessageCargo cargo) {
        MessageUser inputModel = cargo.getProcessedRequest();
        List<String> errors = new ArrayList<>();
        inputModel.setErrors(errors);
        MessageUser checkExistenceInNeo = checkExistenceInNeo(inputModel, errors);
        if(!CollectionUtils.isEmpty(errors)){
            return;
        }
        validateUsername(inputModel,checkExistenceInNeo,errors);
        validateFriends(inputModel,checkExistenceInNeo,errors,cargo);
        checkExistenceInNeo.setUpdatedTimeStamp(cargo.getCurrentTimeStamp());
        checkExistenceInNeo.setErrors(errors);
        cargo.setProcessedRequest(checkExistenceInNeo);
}

    private void validateFriends(MessageUser inputModel, MessageUser checkExistenceInNeo, List<String> errors,MessageCargo cargo) {
        checkExistenceInNeo.setMessageModelList(new ArrayList<>());
        List<String> existingFriends = checkExistenceInNeo.getFriends();
        if(CollectionUtils.isEmpty(inputModel.getFriends())){
            return;
        }
        inputModel.getFriends().forEach(newFriendToAdd->{
            if(existingFriends.contains(newFriendToAdd)){
                errors.add("User with email id:" + newFriendToAdd + " is already a friend");
            }
            validateNewFriend(newFriendToAdd,errors);
            checkExistenceInNeo.setMessageModelList(populateMessageModelListForNewFriendConnection(inputModel.getEmailID(),newFriendToAdd,cargo));
        });
        if(CollectionUtils.isEmpty(errors)) {
            checkExistenceInNeo.getFriends().addAll(inputModel.getFriends());
        }

    }

    private List<MessageModel> populateMessageModelListForNewFriendConnection(String emailID, String newFriendToAdd, MessageCargo cargo) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageClusterIdentifier(UUID.randomUUID().toString());
        messageModel.setIsFriend(Boolean.FALSE.toString());
        messageModel.setReceiverId(newFriendToAdd);
        messageModel.setSenderId(emailID);
        messageModel.setMessages(sendAddMeAsYourFriendRequest(emailID,newFriendToAdd,cargo));
        return Collections.singletonList(messageModel);
    }

    private List<StampedMessage> sendAddMeAsYourFriendRequest(String emailID, String newFriendToAdd, MessageCargo cargo) {List<StampedMessage> stampedMessages = new ArrayList<>();
        StampedMessage senderMessage = new StampedMessage();
        senderMessage.setSenderId(emailID);
        senderMessage.setMessage(Collections.singletonList("Hi " + newFriendToAdd + "! Please add me as my friend"));
        senderMessage.setTimestamp(Collections.singletonList(cargo.getCurrentTimeStamp()));
        StampedMessage receiverMessage = new StampedMessage();
        receiverMessage.setSenderId(newFriendToAdd);
        return Arrays.asList(senderMessage,receiverMessage);
    }

    private void validateNewFriend(String newFriendToAdd, List<String> errors) {
        if(StringUtils.isBlank(newFriendToAdd)){
            errors.add("New friend to be added cannot be blank");
        }
        if(messageUserGraphRepo.findByEmailID(newFriendToAdd) == null){
            errors.add("Friend with emailID : " + newFriendToAdd + " does not exist");
        }
    }

    private void validateUsername(MessageUser inputModel, MessageUser checkExistenceInNeo, List<String> errors) {
        if(StringUtils.isBlank(inputModel.getUsername())){
           return;
        }
        if(StringUtils.equals(checkExistenceInNeo.getUsername(),inputModel.getUsername())) {
            errors.add("Same Username: cannot update!");
        }
        else {
            checkExistenceInNeo.setUsername(inputModel.getUsername());
        }
    }


    private MessageUser checkExistenceInNeo(MessageUser inputModel, List<String> errors) {
        MessageUser userNeoFetched;
        userNeoFetched = messageUserGraphRepo.findByEmailID(inputModel.getEmailID());
        if(userNeoFetched == null){
            errors.add("User does not exist");
        }
        return userNeoFetched;
    }

    public void copyToFetchedObj(MessageUser userFetched, MessageUser fetchedModel) {
        fetchedModel.setUsername(userFetched.getUsername());
        fetchedModel.setCreatedTimeStamp(userFetched.getCreatedTimeStamp());
        fetchedModel.setFriends(userFetched.getFriends());
        fetchedModel.setMessageModelList(userFetched.getMessageModelList());
        fetchedModel.setPassword(userFetched.getPassword());
        fetchedModel.setEmailID(userFetched.getEmailID());
        fetchedModel.setUpdatedTimeStamp(userFetched.getUpdatedTimeStamp());
    }

    public  MessageUser checkreference(MessageUser model, MessageUser fetchedModel){
        fetchedModel.setErrors(Collections.singletonList("x"));
        model.setErrors(Collections.singletonList("x"));
        return null;
    }

}
