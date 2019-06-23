package pl.sda.javadub1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import pl.sda.dublin.MessagingService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HomeController {

    public TextArea messageTextArea;

    public TextField newMessageTextField;
    public Button sendMessageButton;

    private MessagingService messagingService;

    @FXML
    public Label welcomeLabel;

    @FXML
    BorderPane stage;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    // metoda wywolana w momencie klikniecia "polacz z serwerem"
    public void onClick(ActionEvent actionEvent) {
        System.out.println("Nawiazuje polaczenie z serwerem... ");

        int port = 5000;
        String address = "localhost";

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port));
            messagingService = new MessagingService(socket);
            sendMessageButton.setDisable(false);

            Thread listenerThread = new Thread(() -> {
                // tak długo jak socket jest otwarty to będziemy odbierac nowe wiadomosci
                while (!socket.isClosed()) {
                    try {
                        // otrzymujemy odpowiedź od serwera
                        Object response = messagingService.readObject();
                        // doklejamy odpowiedź do głownego tekstu czatu
                        Platform.runLater(() -> {
                            messageTextArea.setText(messageTextArea.getText() + "\n" + "klient:" + response.toString());
                        });
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            listenerThread.start();

//            messagingService.sendObject("Hello-world");
//            String response = (String) messagingService.readObject();
//            System.out.println(response);
        } catch (IOException e) {
            // TODO: 22.06.19 #1 wyswietl okno z informacja o bledzie polaczenia
            System.out.println("Nie udalo sie polaczyc z serwerem " + e.getMessage());
        }
    }


    public void onMessageSent(ActionEvent actionEvent) throws IOException {
        String newMessage = newMessageTextField.getText();
        StringBuilder builder = new StringBuilder();
        String fullMessage = builder.append(messageTextArea.getText()) // pobierz aktualny tekst z kontrolki
                .append("\n") // dodaj nowa linie
                .append("Ja: ") // oznacza nasza wiadomosc
                .append(newMessageTextField.getText()) // dodaj nowy tekst
                .toString();

        messageTextArea.setText(fullMessage);
        System.out.println("Wysylam wiadomosc do serwera... " + newMessage);
        newMessageTextField.clear();
        messagingService.sendObject(newMessage);

    }
}
