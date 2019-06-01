package message.LDM;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.stereotype.Indexed;

@NodeEntity
public class StampedMessage {

    private String senderId;
    private String message;
    @Id
    private String timestamp;

    public StampedMessage() {
    }

    public StampedMessage(String timestamp) {
        this.timestamp = timestamp;
    }

    public StampedMessage(String senderId, String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "StampedMessage{" +
                "senderId='" + senderId + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
