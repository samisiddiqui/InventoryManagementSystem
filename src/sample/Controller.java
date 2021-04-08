package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML private Button ExitButton;


    public void ExitProgram(ActionEvent actionEvent) {
        Stage close = (Stage) ExitButton.getScene().getWindow();
        close.close();
    }
}
