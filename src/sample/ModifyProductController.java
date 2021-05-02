package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sami Siddiqui
 */
public class ModifyProductController implements Initializable {
    public Product workingProduct  = new Product(0,"test",0,-1,1,0);
    public TextField ModifyProductIDTextField;
    public TextField ModifyProductNameTextField;
    public TextField ModifyProductInventoryTextField;
    public TextField ModifyProductPriceTextField;
    public TextField ModifyProductMinTextField;
    public TextField ModifyProductMaxTextField;
    public TextField ModifyProductSearchTextField;

    public TableView<Part> ModifyProductPartTableView;
    public TableColumn<Part, Integer> ModifyProductPartIDColumn;
    public TableColumn<Part, String> ModifyProductPartNameColumn;
    public TableColumn<Part, Integer> ModifyProductPartInventoryColumn;
    public TableColumn<Part, Double> ModifyProductPartPriceColumn;
    public Button ModifyProductAddPartButton;

    public TableView<Part> ModifyProductAssociatedPartTableView;
    public TableColumn<Part, Integer> ModifyProductAssociatedIDColumn;
    public TableColumn<Part, String> ModifyProductAssociatedNameColumn;
    public TableColumn<Part, Integer> ModifyProductAssociatedInventoryColumn;
    public TableColumn<Part, Double> ModifyProductAssociatedPriceColumn;
    public Button ModifyProductRemovePartButton;

    public Button ModifyProductSaveButton;
    public Button ModifyProductCancelButton;
    public Label InventoryIntegerLabel;
    public Label InventoryRangeLabel;
    public Label PriceDoubleLabel;
    public Label MinIntegerLabel;
    public Label MinRangeLabel;
    public Label MaxIntegerLabel;
    public Label MaxRangeLabel;

    /**
     * Adds selected part to product associatedParts
     */
    public void ModifyProductAddPart() {
        Part selected = ModifyProductPartTableView.getSelectionModel().getSelectedItem();
        if (selected != null)
            workingProduct.addAssociatedPart(selected);

    }

    /**
     * Removes selected part from product associatedParts
     */
    public void ModifyProductRemovePart() {
        Part selected = ModifyProductAssociatedPartTableView.getSelectionModel().getSelectedItem();
        workingProduct.deleteAssociatedPart(selected);
    }

    /**
     * Performs sanity check on the data entered in TextFields, then updates product if sanity check passes
     */
    public void ModifyProductSave() {
        boolean sanity = SanityCheck();
        if (sanity) {
            String newName = ModifyProductNameTextField.getText();
            double newPrice = Double.parseDouble(ModifyProductPriceTextField.getText());
            int newStock = Integer.parseInt(ModifyProductInventoryTextField.getText());
            int newMin = Integer.parseInt(ModifyProductMinTextField.getText());
            int newMax = Integer.parseInt(ModifyProductMaxTextField.getText());
            workingProduct.setName(newName);
            workingProduct.setPrice(newPrice);
            workingProduct.setStock(newStock);
            workingProduct.setMin(newMin);
            workingProduct.setMax(newMax);
            Inventory.updateProduct(workingProduct.getId(), workingProduct);
            Stage ModifyPartForm = (Stage) ModifyProductCancelButton.getScene().getWindow();
            ModifyPartForm.close();
        }
    }

    /**
     * Closes Modify Product form
     */
    public void ModifyProductCancel() {
        Stage ModifyPartForm = (Stage) ModifyProductCancelButton.getScene().getWindow();
        ModifyPartForm.close();
    }

    /**
     * Receives selected product from Main form, fills TextFields with information, and updates ModifyProductAssociatedPart TableView
     * @param product the product that is being modified
     */
    public void PassProductFromMain(Product product){
        workingProduct = product;
        ModifyProductIDTextField.setText(String.valueOf(workingProduct.getId()));
        ModifyProductNameTextField.setText(workingProduct.getName());
        ModifyProductInventoryTextField.setText(String.valueOf(workingProduct.getStock()));
        ModifyProductPriceTextField.setText(String.valueOf(workingProduct.getPrice()));
        ModifyProductMinTextField.setText(String.valueOf(workingProduct.getMin()));
        ModifyProductMaxTextField.setText(String.valueOf(workingProduct.getMax()));

        ModifyProductAssociatedPartTableView.setItems(workingProduct.getAllAssociatedParts());
        ModifyProductAssociatedIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductAssociatedNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductAssociatedInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductAssociatedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    /**
     * Initializes ModifyProductAssociatedPart and ModifyProductPart TableViews, and fills TextFields with information
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ModifyProductIDTextField.setText(String.valueOf(workingProduct.getId()));
        ModifyProductNameTextField.setText(workingProduct.getName());
        ModifyProductInventoryTextField.setText(String.valueOf(workingProduct.getStock()));
        ModifyProductPriceTextField.setText(String.valueOf(workingProduct.getPrice()));
        ModifyProductMinTextField.setText(String.valueOf(workingProduct.getMin()));
        ModifyProductMaxTextField.setText(String.valueOf(workingProduct.getMax()));

        ModifyProductAssociatedPartTableView.setItems(workingProduct.getAllAssociatedParts());
        ModifyProductAssociatedIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductAssociatedNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductAssociatedInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductAssociatedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        ModifyProductPartTableView.setItems(Inventory.getAllParts());
        ModifyProductPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
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
            inventory = Integer.parseInt(ModifyProductInventoryTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            InventoryIntegerLabel.setVisible(true);
        }
        //Check if price field has a double
        try {
            Double.parseDouble(ModifyProductPriceTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            PriceDoubleLabel.setVisible(true);
        }
        //Check if min field has an integer
        try {
            min = Integer.parseInt(ModifyProductMinTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            MinIntegerLabel.setVisible(true);
        }
        //Check if max field has an integer
        try {
            max = Integer.parseInt(ModifyProductMaxTextField.getText());
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
    }

    /**
     * Searches associatedParts list for text in part search view, then updates associatedParts TableView
     */
    public void ModifyProductSearch() {
        String search = ModifyProductSearchTextField.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        try {
            int IDSearch = Integer.parseInt(search);
            if (Inventory.lookupPart(IDSearch) != null)
                searchResults.add(Inventory.lookupPart(IDSearch));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupPart(search);
        }
        ModifyProductPartTableView.setItems(searchResults);
        if (searchResults.size() == 0)
            ModifyProductPartTableView.setPlaceholder(new Label("No results matched " + search));
    }
}
