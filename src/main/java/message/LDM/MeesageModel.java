package message.LDM;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Map;
@NodeEntity
public class MeesageModel  {
    @Id
    String messageClusterIdentifier;
    @Relationship(type = "MESSAGE_FROM")
    List<StampedMessage> messages;
    @Relationship(type = "CLUSTER_OF",direction = Relationship.UNDIRECTED)
    List<MessageUser> messageUsers;

    public MeesageModel(String messageClusterIdentifier,List<StampedMessage> messages, List<MessageUser> messageUsers) {
        this.messageClusterIdentifier = messageClusterIdentifier;
        this.messages = messages;
        this.messageUsers = messageUsers;
    }

    public String getMessageClusterIdentifier() {
        return messageClusterIdentifier;
    }

    public void setMessageClusterIdentifier(String messageClusterIdentifier) {
        this.messageClusterIdentifier = messageClusterIdentifier;
    }

    public List<StampedMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<StampedMessage>  messages) {
        this.messages = messages;
    }

    public List<MessageUser> getMessageUsers() {
        return messageUsers;
    }

    public void setMessageUsers(List<MessageUser> messageUsers) {
        this.messageUsers = messageUsers;
    }

    @Override
    public String toString() {
        return "MeesageModel{" +
                "messageClusterIdentifier='" + messageClusterIdentifier + '\'' +
                ", messages=" + messages +
                ", messageUsers=" + messageUsers +
                '}';
    }
}
