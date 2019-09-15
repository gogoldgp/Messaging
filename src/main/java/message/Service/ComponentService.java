package message.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import message.Utils.JsonUtils;
import message.Validator.UserValidator;
import message.jpa.LDM.MessageCargo;
import message.jpa.LDM.MessageUser;
import message.jpa.repositories.MessageUserGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Logger;

import static message.Utils.JsonUtils.*;
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
    @Autowired
    UserValidator userValidator;

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public MessageUser save(MessageUser model) throws IOException {
        MessageCargo cargo = new MessageCargo();
        preProcess(model,cargo);
        String actionType = Objects.isNull(model.getActionType())?"":model.getActionType();
        switch(actionType){
            case "Create": {
                userValidator.validateForUserCreate(cargo);
                if(!CollectionUtils.isEmpty(cargo.getProcessedRequest().getErrors())){
                    return new MessageUser(cargo.getProcessedRequest().getErrors());
                }
                LOGGER.info("User validated for create!! Proceeding to Create User");
               userSaveService.createNewUser(cargo.getProcessedRequest());
               break;
            }
            case "Update":{
                userValidator.validateForUserUpdate(cargo);
                MessageUser processedModel = cargo.getProcessedRequest();
                if(!CollectionUtils.isEmpty(processedModel.getErrors())){
                    return new MessageUser(processedModel.getErrors());
                }
                LOGGER.info("User validated for update!! Proceeding to Update User and Friends");
                model = userSaveService.updateExistingUser(processedModel);
                break;
            }
            default: {
                model.setErrors(Collections.singletonList("Unknown actionType provided"));
            }

        }
        return model;
    }

    private void preProcess(MessageUser model, MessageCargo cargo) throws IOException {
        cargo.setCurrentTimeStamp(timestamp());
        cargo.setInputRequest(model);
        MessageUser clonedObject = convertStringToObject(convertObjectToString(model),MessageUser.class);
        cargo.setProcessedRequest(clonedObject);
    }
    private String timestamp() {
        return new Timestamp(new java.util.Date().getTime()).toString();
    }

}
