package message.jpa.LDM;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class MessageModel {

    @Id
    String messageClusterIdentifier;
    String isFriend;
    String senderId;
    String receiverId;
    @Relationship(type = "MESSAGE_FROM")
    List<StampedMessage> messages;

    public String getMessageClusterIdentifier() {
        return messageClusterIdentifier;
    }

    public void setMessageClusterIdentifier(String messageClusterIdentifier) {
        this.messageClusterIdentifier = messageClusterIdentifier;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public List<StampedMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<StampedMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "messageClusterIdentifier='" + messageClusterIdentifier + '\'' +
                ", isFriend='" + isFriend + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", messages=" + messages +
                '}';
    }
}
