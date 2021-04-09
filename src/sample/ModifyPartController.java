package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartController {
    public RadioButton ModifyPartInHouse;
    public ToggleGroup ModifyPartToggle;
    public RadioButton ModifyPartOutsourced;
    public Label ModifyPartToggleLabel;
    public Button ModifyPartCancelButton;

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
}
