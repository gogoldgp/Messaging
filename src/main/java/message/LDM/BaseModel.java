package message.LDM;

public class BaseModel {
    private String createdTimeStamp;
    private String updatedTimeStamp;
    private String actionType;

    public BaseModel() {
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

    @Override
    public String toString() {
        return "BaseModel{" +
                "createdTimeStamp='" + createdTimeStamp + '\'' +
                ", updatedTimeStamp='" + updatedTimeStamp + '\'' +
                ", actionType='" + actionType + '\'' +
                '}';
    }
}
