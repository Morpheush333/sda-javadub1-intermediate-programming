package pl.sda.javadub1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.sda.dublin.MessagingService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HomeController {

    private MessagingService messagingService;

    @FXML
    public Label welcomeLabel;

    @FXML
    VBox stage;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void onClick(ActionEvent actionEvent) {
        System.out.println("Nawiazuje polaczenie z serwerem... ");

        int port = 5000;
        String address = "localhost";

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port));

            messagingService = new MessagingService(socket);
            messagingService.sendObject("Hello-world");
            String response = (String) messagingService.readObject();
            System.out.println(response);
        } catch (IOException e) {
            // TODO: 22.06.19 #1 wyswietl okno z informacja o bledzie polaczenia
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
