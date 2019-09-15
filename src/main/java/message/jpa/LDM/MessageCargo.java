package message.jpa.LDM;

public class MessageCargo {
    private MessageUser inputRequest;
    private MessageUser processedRequest;
    private String currentTimeStamp;

    public MessageUser getInputRequest() {
        return inputRequest;
    }

    public void setInputRequest(MessageUser inputRequest) {
        this.inputRequest = inputRequest;
    }

    public MessageUser getProcessedRequest() {
        return processedRequest;
    }

    public void setProcessedRequest(MessageUser processedRequest) {
        this.processedRequest = processedRequest;
    }

    public String getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(String currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    @Override
    public String toString() {
        return "MessageCargo{" +
                "inputRequest=" + inputRequest +
                ", processedRequest=" + processedRequest +
                ", currentTimeStamp='" + currentTimeStamp + '\'' +
                '}';
    }
}
