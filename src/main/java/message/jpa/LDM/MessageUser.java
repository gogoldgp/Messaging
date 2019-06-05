package message.jpa.LDM;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class MessageUser extends BaseModel  {
    private String emailID;
    private List<MeesageModel> meesageModelList;
    private String username;
    private String password;
    private List<String> friends;

    @Id
    @GeneratedValue
    private Long messageNodeIdentifier;

    public MessageUser() {
    }

    public MessageUser(List<String> errors) {
        super(errors);
    }

    public MessageUser(String emailID, List<MeesageModel> meesageModelList, String username, String password, List<String> friends, Long messageNodeIdentifier,String createdTimestamp) {
        super(createdTimestamp);
        this.emailID = emailID;
        this.meesageModelList = meesageModelList;
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

    public List<MeesageModel> getMeesageModelList() {
        return meesageModelList;
    }

    public void setMeesageModelList(List<MeesageModel> meesageModelList) {
        this.meesageModelList = meesageModelList;
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

    @Override
    public String toString() {
        return "MessageUser{" +
                "emailID='" + emailID + '\'' +
                ", meesageModelList=" + meesageModelList +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + friends +
                ", messageNodeIdentifier=" + messageNodeIdentifier +
                '}';
    }
}