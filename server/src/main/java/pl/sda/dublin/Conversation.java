package pl.sda.dublin;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<String> messages = new ArrayList<>();
    private ClientService firstClient;
    private ClientService secondClient;


    public void addMsg(String msg) {
        this.messages.add(msg);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public ClientService getFirstClient() {
        return firstClient;
    }

    public void setFirstClient(ClientService firstClient) {
        this.firstClient = firstClient;
    }

    public ClientService getSecondClient() {
        return secondClient;
    }

    public void setSecondClient(ClientService secondClient) {
        this.secondClient = secondClient;
    }

    public ClientService getOtherClient(ClientService clientService) {
        if (clientService == firstClient) {
            return secondClient;
        } else if (clientService == secondClient) {
            return firstClient;
        } else {
            throw new RuntimeException("Wrong id of client");
        }
    }
}
