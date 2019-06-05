package message.jpa.LDM;

import org.neo4j.ogm.annotation.Transient;

import java.util.List;


public class BaseModel {
    private String createdTimeStamp;
    private String updatedTimeStamp;
    @Transient
    private String actionType;
    @Transient
    private List<String> errors;

    public BaseModel() {
    }

    public BaseModel(String createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(String createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(String updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public BaseModel(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "createdTimeStamp='" + createdTimeStamp + '\'' +
                ", updatedTimeStamp='" + updatedTimeStamp + '\'' +
                ", actionType='" + actionType + '\'' +
                ", errors=" + errors +
                '}';
    }
}
