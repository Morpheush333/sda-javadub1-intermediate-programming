package pl.sda.javadub1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HomeController {

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
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.write("Hello server\n");
            writer.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Wiadomosc od serwera: " + reader.readLine());
        } catch (IOException e) {
            // TODO: 22.06.19 #1 wyswietl okno z informacja o bledzie polaczenia
            e.printStackTrace();
        }
    }


}
