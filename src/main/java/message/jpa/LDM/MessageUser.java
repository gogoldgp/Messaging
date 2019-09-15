package message.jpa.LDM;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class MessageUser extends BaseModel  {
    @Id
    private String emailID;
    private List<MessageModel> messageModelList;
    private String username;
    private String password;
    private List<String> friends;

    @Override
    public String toString() {
        return "MessageUser{" +
                "emailID='" + emailID + '\'' +
                ", messageModelList=" + messageModelList +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + friends +
                ", messageNodeIdentifier=" + messageNodeIdentifier +
                '}';
    }


    @Id
    @GeneratedValue
    private Long messageNodeIdentifier;

    public MessageUser() {
    }

    public MessageUser(List<String> errors) {
        super(errors);
    }

    public MessageUser(String emailID, List<MessageModel> messageModelList, String username, String password, List<String> friends, Long messageNodeIdentifier, String createdTimestamp) {
        super(createdTimestamp);
        this.emailID = emailID;
        this.messageModelList = messageModelList;
        this.username = username;
        this.password = password;
        this.friends = friends;
        this.messageNodeIdentifier = messageNodeIdentifier;
    }

    public MessageUser(String emailID) {
        this.emailID = emailID;
    }

    public MessageUser(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public List<MessageModel> getMessageModelList() {
        return messageModelList;
    }

    public void setMessageModelList(List<MessageModel> messageModelList) {
        this.messageModelList = messageModelList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Long getMessageNodeIdentifier() {
        return messageNodeIdentifier;
    }

    public void setMessageNodeIdentifier(Long messageNodeIdentifier) {
        this.messageNodeIdentifier = messageNodeIdentifier;
    }

}