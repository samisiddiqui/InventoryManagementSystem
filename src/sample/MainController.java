package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public TableView PartsTableView;
    @FXML private Button DeletePartButton;
    @FXML private Button ModifyPartButton;
    @FXML private Button AddPartButton;

    @FXML private Button DeleteProductButton;
    @FXML private Button ModifyProductButton;
    @FXML private Button AddProductButton;

    @FXML public Button ExitButton;

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
        AddPartForm.show();
        AddPartForm.setResizable(false);
    }

    public void CallModifyPartForm(ActionEvent actionEvent) throws IOException {
        Stage ModifyPartForm = new Stage();
        Stage MainForm = (Stage) ExitButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModifyPartForm.fxml"));
        ModifyPartForm.setTitle("Add Part");
        ModifyPartForm.setScene(new Scene(root, 600, 400));
        ModifyPartForm.show();
        ModifyPartForm.setResizable(false);
    }
}
