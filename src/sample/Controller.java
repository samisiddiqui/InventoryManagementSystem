package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private Button DeletePartButton;
    @FXML private Button ModifyPartButton;
    @FXML private Button AddPartButton;
    @FXML private Button DeleteProductButton;
    @FXML private Button ModifyProductButton;
    @FXML private Button AddProductButton;
    @FXML private Button ExitButton;

    public void ExitProgram(ActionEvent actionEvent) {
        Stage close = (Stage) ExitButton.getScene().getWindow();
        close.close();
    }

    public void CallAddPartForm(ActionEvent actionEvent) throws IOException {
        Stage AddPartForm = new Stage();
        Stage MainForm = (Stage) ExitButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        AddPartForm.setTitle("Add Part");
        AddPartForm.setScene(new Scene(root, 600, 400));
        MainForm.hide();
        AddPartForm.show();
        AddPartForm.setResizable(false);
    }
}
