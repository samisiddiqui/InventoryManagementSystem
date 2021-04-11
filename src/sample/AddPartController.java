package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPartController {

    public int IDCounter = 1;
    public RadioButton AddPartInHouse;
    public Label AddPartToggleLabel;
    public RadioButton AddPartOutsourced;
    public Button AddPartCancelButton;
    public ToggleGroup AddPartToggle;
    public Button AddPartSaveButton;

    public TextField AddPartIDTextField;
    public TextField AddPartNameTextField;
    public TextField AddPartInventoryTextField;
    public TextField AddPartPriceTextField;
    public TextField AddPartMinTextField;
    public TextField AddPartToggleTextField;
    public TextField AddPartMaxTextField;

    public void AddPartToggleLabelToggle(ActionEvent actionEvent) {
        if (AddPartInHouse.isSelected() == true)
            AddPartToggleLabel.setText("Machine ID");
        else
            AddPartToggleLabel.setText("Company Name");
    }

    public void AddPartCancel(ActionEvent actionEvent) {
        Stage AddPartForm = (Stage) AddPartCancelButton.getScene().getWindow();
        AddPartForm.close();
    }

    public void AddPartSave(ActionEvent actionEvent) {
        String newName = AddPartNameTextField.getText();
        double newPrice = Double.parseDouble(AddPartPriceTextField.getText());
        int newStock = Integer.parseInt(AddPartInventoryTextField.getText());
        int newMin = Integer.parseInt(AddPartMinTextField.getText());
        int newMax = Integer.parseInt(AddPartMaxTextField.getText());
        InHouse newInHouse = null;
        Outsourced newOutsourced = null;

        if (AddPartInHouse.isSelected() == true)
            newInHouse = new InHouse(IDCounter, newName, newPrice, newStock, newMin, newMax, Integer.parseInt(AddPartToggleTextField.getText()));
        else {
            newOutsourced = new Outsourced(IDCounter, newName, newPrice, newStock, newMin, newMax, AddPartToggleTextField.getText());
        }
    }
}
