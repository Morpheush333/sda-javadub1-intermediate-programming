package pl.sda.javadub1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class PrimaryController {

    @FXML
    public Label welcomeLabel;

    @FXML
    VBox stage;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void onClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage.getScene().getWindow());

        String absolutePath = file.getAbsolutePath();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tytul");
        alert.setHeaderText("Tekst naglowka");
        alert.setContentText(absolutePath);

        alert.showAndWait();

    }


}
