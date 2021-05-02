package sample;

import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * @author Sami Siddiqui
 */
public class AddPartController {
    public static int IDCounter = 3;
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
    public void AddPartToggleLabelToggle() {
        if (AddPartInHouse.isSelected())
            AddPartToggleLabel.setText("Machine ID");
        else
            AddPartToggleLabel.setText("Company Name");
    }

    /**
     * Closes Add Part form
     */
    public void AddPartCancel() {
        Stage AddPartForm = (Stage) AddPartCancelButton.getScene().getWindow();
        AddPartForm.close();
    }

    /**
     * Performs sanity check on the data entered in TextFields, then adds a new part to Inventory if sanity check passes
     */
    public void AddPartSave() {
        boolean sanity = SanityCheck();
        if (sanity) {
            String newName = AddPartNameTextField.getText();
            double newPrice = Double.parseDouble(AddPartPriceTextField.getText());
            int newStock = Integer.parseInt(AddPartInventoryTextField.getText());
            int newMin = Integer.parseInt(AddPartMinTextField.getText());
            int newMax = Integer.parseInt(AddPartMaxTextField.getText());
            InHouse newInHouse;
            Outsourced newOutsourced;
            IDCounter++;
            if (AddPartInHouse.isSelected()) {
                newInHouse = new InHouse(IDCounter, newName, newPrice, newStock, newMin, newMax, Integer.parseInt(AddPartToggleTextField.getText()));
                Inventory.addPart(newInHouse);
            } else {
                newOutsourced = new Outsourced(IDCounter, newName, newPrice, newStock, newMin, newMax, AddPartToggleTextField.getText());
                Inventory.addPart(newOutsourced);
            }
            Stage AddPartForm = (Stage) AddPartCancelButton.getScene().getWindow();
            AddPartForm.close();
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
            inventory = Integer.parseInt(AddPartInventoryTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            InventoryIntegerLabel.setVisible(true);
        }
        //Check if price field has a double
        try {
            Double.parseDouble(AddPartPriceTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            PriceDoubleLabel.setVisible(true);
        }
        //Check if min field has an integer
        try {
            min = Integer.parseInt(AddPartMinTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            MinIntegerLabel.setVisible(true);
        }
        //Check if max field has an integer
        try {
            max = Integer.parseInt(AddPartMaxTextField.getText());
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
        if (AddPartInHouse.isSelected()) {
            try {
                Integer.parseInt(AddPartToggleTextField.getText());
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
