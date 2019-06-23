package pl.sda.dublin;

import java.io.IOException;
import java.net.Socket;

import static java.util.Objects.requireNonNull;

public class ClientService {
    private Socket socket;
    private MessagingService messagingService;

    public ClientService(Socket socket) {
        this.socket = requireNonNull(socket);
        messagingService = new MessagingService(socket);
    }

    public void handleCommunication() {
        System.out.println("Rozpoczynam komunikacje.............");

        while (true) {
            try {
                Object msg = messagingService.readObject();
                if (msg instanceof String) {
                    String message = (String) msg;
                    System.out.println("Otrzymałem wiadomość: " + message);
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
