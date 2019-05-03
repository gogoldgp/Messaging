package message.LDM;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class MessageUser {
    private String emailID;
    private List<String> incomingMessageQueue;
    private List<String> outgoingMessageQueue;
    private String username;
    private String password;

    @Relationship(type = "MUTUAL_OF",direction = Relationship.UNDIRECTED)
    private List<MessageUser> friends;

    @Id @GeneratedValue
    private Long messageNodeIdentifier;
    @Override
    public String toString() {
        return "MessageUser{" +
                "emailID='" + emailID + '\'' +
                ", incomingMessageQueue=" + incomingMessageQueue +
                ", outgoingMessageQueue=" + outgoingMessageQueue +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", messageNodeIdentifier='" + messageNodeIdentifier + '\'' +
                '}';
    }

    public Long getMessageNodeIdentifier() {
        return messageNodeIdentifier;
    }

    public void setMessageNodeIdentifier(Long messageNodeIdentifier) {
        this.messageNodeIdentifier = messageNodeIdentifier;
    }

    public List<MessageUser> getFriends() {
        return friends;
    }

    public void setFriends(List<MessageUser> friends) {
        this.friends = friends;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public List<String> getIncomingMessageQueue() {
        return incomingMessageQueue;
    }

    public void setIncomingMessageQueue(List<String> incomingMessageQueue) {
        this.incomingMessageQueue = incomingMessageQueue;
    }

    public List<String> getOutgoingMessageQueue() {
        return outgoingMessageQueue;
    }

    public void setOutgoingMessageQueue(List<String> outgoingMessageQueue) {
        this.outgoingMessageQueue = outgoingMessageQueue;
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

}
