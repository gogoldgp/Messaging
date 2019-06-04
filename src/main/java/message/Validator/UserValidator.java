package message.Validator;

import message.LDM.MessageUser;
import message.repositories.MessageUserGraphRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserValidator {

    public static MessageUser validateForUserCreate(MessageUser user, MessageUserGraphRepo messageUserGraphRepo){
        MessageUser model  = user;
        if(model.getEmailID()== null  || model.getPassword()==null){
            model.setErrors(Collections.singletonList("Both emailID and password is mandatory"));
        }
        else if(messageUserGraphRepo.findByEmailID(model.getEmailID())!=null){
            model.setErrors(Collections.singletonList("Duplicate Account"));
        }
        return model;
    }
    public static MessageUser validateForUserUpdate(MessageUser model, MessageUserGraphRepo messageUserGraphRepo, MessageUser fetchedModel) {
        List<String> errors = new ArrayList<>();
        fetchedModel.setErrors(errors);
        MessageUser userFetched = messageUserGraphRepo.findByEmailID(model.getEmailID());
        copyToFetchedObj(userFetched,fetchedModel);
        if(messageUserGraphRepo.findByEmailID(model.getEmailID()) == null){
            errors.add("User doesn't exist for update!");
        }
        else if(StringUtils.equals(fetchedModel.getUsername(),model.getUsername())){
            errors.add("Same Username: cannot update!");
        }
        else if((model.getFriends()==null || CollectionUtils.isEmpty(model.getFriends())) && StringUtils.isEmpty(model.getUsername())){
            errors.add("No username or friends provided. Nothing to update!");
        }



        else if(!CollectionUtils.isEmpty(model.getFriends())){
            List<String> fetchedFriendEmailIDs = new ArrayList<>();
            if(!CollectionUtils.isEmpty(fetchedModel.getFriends())) {
                fetchedFriendEmailIDs = new ArrayList<>(fetchedModel.getFriends());
            }
            List<String> finalFetchedFriendEmailIDs = fetchedFriendEmailIDs;
            model.getFriends().forEach(friendEmail -> {
                if (friendEmail == null) {
                    errors.add("No emailID provided for friend to be added!");
                } else if (messageUserGraphRepo.findByEmailID(friendEmail) == null) {
                    errors.add("User with email ID: " + friendEmail + " does not exist!");
                } else if (!CollectionUtils.isEmpty(finalFetchedFriendEmailIDs) && finalFetchedFriendEmailIDs.contains(friendEmail)) {
                    errors.add("User with email id:" + friendEmail + " is already a friend");
                }
            });
        }

        model.setErrors(errors);
        return null;
}

    public static void copyToFetchedObj(MessageUser userFetched, MessageUser fetchedModel) {
        fetchedModel.setUsername(userFetched.getUsername());
        fetchedModel.setCreatedTimeStamp(userFetched.getCreatedTimeStamp());
        fetchedModel.setFriends(userFetched.getFriends());
        fetchedModel.setMeesageModelList(userFetched.getMeesageModelList());
        fetchedModel.setPassword(userFetched.getPassword());
        fetchedModel.setEmailID(userFetched.getEmailID());
        fetchedModel.setUpdatedTimeStamp(userFetched.getUpdatedTimeStamp());
    }

    public static MessageUser checkreference(MessageUser model, MessageUser fetchedModel){
        fetchedModel.setErrors(Collections.singletonList("x"));
        model.setErrors(Collections.singletonList("x"));
        return null;
    }

}
