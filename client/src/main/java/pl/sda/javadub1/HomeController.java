package pl.sda.javadub1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    }


}
