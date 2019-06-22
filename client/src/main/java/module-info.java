module pl.sda.javadub1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.sda.javadub1 to javafx.fxml;
    exports pl.sda.javadub1;
}