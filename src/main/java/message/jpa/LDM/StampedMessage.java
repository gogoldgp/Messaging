package message.jpa.LDM;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class StampedMessage {

    @Id
    private String senderId;
    private List<String> message;
    private List<String> timestamp;

    public StampedMessage() {
    }

    public StampedMessage(String senderId, List<String> message) {
        this.senderId = senderId;
        this.message = message;
    }

    public StampedMessage(String senderId, List<String> message, List<String> timestamp) {
        this.senderId = senderId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<String> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<String> timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StampedMessage{" +
                "senderId='" + senderId + '\'' +
                ", message=" + message +
                ", timestamp=" + timestamp +
                '}';
    }
}
