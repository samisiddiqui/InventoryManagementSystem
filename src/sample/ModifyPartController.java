package sample;

import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * @author Sami Siddiqui
 */
public class ModifyPartController {
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
    public Label InventoryIntegerLabel;
    public Label InventoryRangeLabel;
    public Label PriceDoubleLabel;
    public Label MinIntegerLabel;
    public Label MinRangeLabel;
    public Label ToggleIdentityLabel;
    public Label MaxIntegerLabel;
    public Label MaxRangeLabel;

    /**
     * Adjusts label based on whether In-House or Outsourced
     */
    public void ModifyPartToggleLabelToggle() {
        if (ModifyPartInHouse.isSelected())
            ModifyPartToggleLabel.setText("Machine ID");
        else
            ModifyPartToggleLabel.setText("Company Name");
    }

    /**
     * Closes Modify Part form
     */
    public void ModifyPartCancel() {
        Stage ModifyPartForm = (Stage) ModifyPartCancelButton.getScene().getWindow();
        ModifyPartForm.close();
    }

    /**
     * Performs sanity check on the data entered in TextFields, then updates part if sanity check passes
     */
    public void ModifyPartSave() {
        InHouse updatedInHouse;
        Outsourced updatedOutsourced;
        boolean sanity = SanityCheck();
        if (sanity) {
            if (ModifyPartInHouse.isSelected()) {
                updatedInHouse = new InHouse(Integer.parseInt(ModifyPartIDTextField.getText()), ModifyPartNameTextField.getText(), Double.parseDouble(ModifyPartPriceTextField.getText()), Integer.parseInt(ModifyPartInventoryTextField.getText()), Integer.parseInt(ModifyPartMinTextField.getText()), Integer.parseInt(ModifyPartMaxTextField.getText()), Integer.parseInt(ModifyPartToggleTextField.getText()));
                Inventory.updatePart(Integer.parseInt(ModifyPartIDTextField.getText()), updatedInHouse);
            } else {
                updatedOutsourced = new Outsourced(Integer.parseInt(ModifyPartIDTextField.getText()), ModifyPartNameTextField.getText(), Double.parseDouble(ModifyPartPriceTextField.getText()), Integer.parseInt(ModifyPartInventoryTextField.getText()), Integer.parseInt(ModifyPartMinTextField.getText()), Integer.parseInt(ModifyPartMaxTextField.getText()), ModifyPartToggleTextField.getText());
                Inventory.updatePart(Integer.parseInt(ModifyPartIDTextField.getText()), updatedOutsourced);
            }
            Stage ModifyPartForm = (Stage) ModifyPartCancelButton.getScene().getWindow();
            ModifyPartForm.close();
        }
    }

    /**
     * Checks if values in TextFields match data types and make logical sense
     * @return true if data passes all sanity checks, false otherwise
     */
    public boolean SanityCheck() {
        HideErrorLabels();
        boolean pass = true;
        int inventory = -1;
        int min = -1;
        int max = -1;

        //Check if inventory field has an integer
        try {
            inventory = Integer.parseInt(ModifyPartInventoryTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            InventoryIntegerLabel.setVisible(true);
        }
        //Check if price field has a double
        try {
            Double.parseDouble(ModifyPartPriceTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            PriceDoubleLabel.setVisible(true);
        }
        //Check if min field has an integer
        try {
            min = Integer.parseInt(ModifyPartMinTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            MinIntegerLabel.setVisible(true);
        }
        //Check if max field has an integer
        try {
            max = Integer.parseInt(ModifyPartMaxTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            MaxIntegerLabel.setVisible(true);
        }
        //Check if min is less than max
        if (min > max) {
            pass = false;
            MinRangeLabel.setVisible(true);
            MaxRangeLabel.setVisible(true);
        }
        //Check if inventory is between min and max
        if (inventory > max || inventory < min) {
            pass = false;
            InventoryRangeLabel.setVisible(true);
        }
        //If part is an InHouse, check if machine ID field is an integer
        if (ModifyPartInHouse.isSelected()) {
            try {
                Integer.parseInt(ModifyPartToggleTextField.getText());
            } catch (NumberFormatException e) {
                pass = false;
                ToggleIdentityLabel.setVisible(true);
                ToggleIdentityLabel.setText("Value must be Integer");
            }
        }
        return pass;
    }

    /**
     * Hides all error labels, so SanityCheck() failures only show current failures
     */
    public void HideErrorLabels() {
        InventoryIntegerLabel.setVisible(false);
        InventoryRangeLabel.setVisible(false);
        PriceDoubleLabel.setVisible(false);
        MinIntegerLabel.setVisible(false);
        MinRangeLabel.setVisible(false);
        MaxIntegerLabel.setVisible(false);
        MaxRangeLabel.setVisible(false);
        ToggleIdentityLabel.setVisible(false);
    }
}
