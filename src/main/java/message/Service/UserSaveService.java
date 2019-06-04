package message.Service;

import message.LDM.MessageUser;
import message.Validator.UserValidator;
import message.repositories.MessageUserGraphRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Collections;

@Component
public class UserSaveService {
    @Autowired
    MessageUserGraphRepo messageUserGraphRepo;

    public MessageUser createNewUser(MessageUser model) {
        populateUserModel(model);
        messageUserGraphRepo.save(model);
        return model;
    }


    private void populateUserModel(MessageUser model) {
        if (model.getUsername() == null) {
            model.setUsername(model.getEmailID());
        }
        model.setCreatedTimeStamp(timestamp());
    }

    private String timestamp() {
        return new Timestamp(new java.util.Date().getTime()).toString();
    }

    public MessageUser updateExistingUser(MessageUser model, MessageUser fetchedModel) {
        MessageUser user = populateModelForUpdate(model, fetchedModel);
        String username = user.getUsername();
        if(fetchedModel.getFriends() == null){
            fetchedModel.setFriends(user.getFriends());
        }else{
            fetchedModel.getFriends().addAll(user.getFriends());
        }
        messageUserGraphRepo.setNewFriends(user.getEmailID(),fetchedModel.getFriends());

        if(!CollectionUtils.isEmpty(user.getFriends())) {
            user.getFriends().forEach(newFriend -> messageUserGraphRepo.createMutualFriendAndUpdateUsername(user.getEmailID(), newFriend,username));
            user.setFriends(fetchedModel.getFriends());
        }
        else{
            messageUserGraphRepo.setNewUsername(user.getEmailID(),username);
        }

        return user;
    }

    private MessageUser populateModelForUpdate(MessageUser model, MessageUser fetchedModel) {
        MessageUser user = new MessageUser();
        UserValidator.copyToFetchedObj(fetchedModel,user);
        user.setFriends(model.getFriends());
        user.setUpdatedTimeStamp(timestamp());
        if(StringUtils.isNotBlank(model.getUsername())) {
            user.setUsername(model.getUsername());
        }
        return user;
    }
}
