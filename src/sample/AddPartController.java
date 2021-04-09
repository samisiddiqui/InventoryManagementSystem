package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class AddPartController {

    public RadioButton AddPartInHouse;
    public Label AddPartToggleLabel;
    public RadioButton AddPartOutsourced;
    public Button AddPartCancelButton;

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
}
