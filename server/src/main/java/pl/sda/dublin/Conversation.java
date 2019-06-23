package pl.sda.dublin;

public class Conversation {

    private ClientService firstClient;
    private ClientService secondClient;


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

    // na podstawie referencji zwroc druga strone rozmowy
    // zwraca klienta po drugiej stronie
    public ClientService getOtherClient(ClientService clientService) {
        if (firstClient.equals(clientService)) {
            return secondClient;
        } else {
            return firstClient;
        }

    }
}
