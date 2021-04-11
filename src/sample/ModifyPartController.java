package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    public RadioButton ModifyPartInHouse;
    public ToggleGroup ModifyPartToggle;
    public RadioButton ModifyPartOutsourced;
    public Label ModifyPartToggleLabel;
    public Button ModifyPartCancelButton;
    public TextField ModifyPartIDTextField;
    public TextField ModifyPartNameTextField;
    public TextField ModifyPartInventoryTextField;
    public TextField ModifyPartPriceTextField;
    public TextField ModifyPartMinTextField;
    public TextField ModifyPartToggleTextField;
    public TextField ModifyPartMaxTextField;
    public Button ModifyPartSaveButton;

    public void ModifyPartToggleLabelToggle(ActionEvent actionEvent) {
        if (ModifyPartInHouse.isSelected() == true)
            ModifyPartToggleLabel.setText("Machine ID");
        else
            ModifyPartToggleLabel.setText("Company Name");
    }

    public void ModifyPartCancel(ActionEvent actionEvent) {
        Stage ModifyPartForm = (Stage) ModifyPartCancelButton.getScene().getWindow();
        ModifyPartForm.close();
    }
    public void LoadData(Part part) {
        ModifyPartIDTextField.setText(String.valueOf(part.getId()));
        ModifyPartNameTextField.setText(part.getName());
        ModifyPartInventoryTextField.setText(String.valueOf(part.getStock()));
        ModifyPartPriceTextField.setText(String.valueOf(part.getPrice()));
        ModifyPartMinTextField.setText(String.valueOf(part.getMin()));
        ModifyPartMaxTextField.setText(String.valueOf(part.getMax()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ModifyPartSave(ActionEvent actionEvent) {
    }
}
