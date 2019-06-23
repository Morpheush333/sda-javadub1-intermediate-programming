package pl.sda.dublin;

import java.io.IOException;
import java.net.Socket;

import static java.util.Objects.requireNonNull;

public class ClientService {
    private Socket socket;
    private MessagingService messagingService;
    private Conversation conversation;

    public ClientService(Socket socket, Conversation conversation) {
        this.socket = requireNonNull(socket);
        messagingService = new MessagingService(socket);
        this.conversation = conversation;
    }

    public void handleCommunication() {
        System.out.println("Rozpoczynam komunikacje.............");

        while (true) {
            try {
                Object msg = messagingService.readObject();
                if (msg instanceof String) {
                    String message = (String) msg;
                    System.out.println("Otrzymałem wiadomość: " + message);
                    ClientService otherClient = conversation.getOtherClient(this);
                    // jesli tylko 1 klient sie polaczy i nie ma drugiego, to nie mamy gdzie wyslac wiadomosci
                    if (otherClient != null) {
                        // jesli jest drugi klient to wysylamy do niego wiadomosc
                        otherClient.messagingService.sendObject(message);
                    }

                } else {
                    System.out.println("Nie zrozumiały format wiadomości");
                }
            } catch (IOException | ClassNotFoundException e) {
                // TODO: 23.06.19 Implement closing of socket in case of failure
                // messagingService.close();
                break;
            }
        }
    }
}
