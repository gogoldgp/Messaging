package message.Service;

import message.jpa.LDM.MessageUser;
import message.jpa.repositories.MessageUserGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.logging.Logger;

import static message.Validator.UserValidator.*;


@Component
public class ComponentService {
    @Autowired
    MessageUserGraphRepo messageUserGraphRepo;
    @Autowired
    UserSaveService userSaveService;
    private static Logger LOGGER  = Logger.getLogger(ComponentService.class.getName());
    @Autowired
    MessageService messageService;

    @Transactional(value = "neo4jTransactionManager", rollbackFor = Exception.class)
    public MessageUser save(MessageUser model) {
        String actionType = model.getActionType();
        switch(actionType){
            case "Create": {
                validateForUserCreate(model,messageUserGraphRepo);
                if(!CollectionUtils.isEmpty(model.getErrors())){
                    return new MessageUser(model.getErrors());
                }
                LOGGER.info("User validated for create!! Proceeding to Create User");
               userSaveService.createNewUser(model);
               break;
            }
            case "Update":{
                MessageUser fetchedModel = new MessageUser(new ArrayList<>());
                validateForUserUpdate(model,messageUserGraphRepo,fetchedModel);
                if(!CollectionUtils.isEmpty(model.getErrors())){
                    return new MessageUser(model.getErrors());
                }
                LOGGER.info("User validated for update!! Proceeding to Update User and Friends");
                model = userSaveService.updateExistingUser(model,fetchedModel);
                break;
            }

        }
        return model;
    }
}
