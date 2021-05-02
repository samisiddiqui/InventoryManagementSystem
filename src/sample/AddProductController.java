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
public class AddProductController implements Initializable {
    public static int IDCounter = 3;
    public Product workingProduct = new Product(0, "test", 0, -1, 1, 0);
    public TextField AddProductIDTextField;
    public TextField AddProductNameTextField;
    public TextField AddProductInventoryTextField;
    public TextField AddProductPriceTextField;
    public TextField AddProductMinTextField;
    public TextField AddProductMaxTextField;
    public TextField AddProductSearchTextField;

    public TableView<Part> AddProductPartTableView;
    public TableColumn<Part, Integer> AddProductPartIDColumn;
    public TableColumn<Part, String> AddProductPartNameColumn;
    public TableColumn<Part, Integer> AddProductPartInventoryColumn;
    public TableColumn<Part, Double> AddProductPriceColumn;
    public Button AddProductAddPartButton;

    public TableView<Part> AddProductAssociatedPartTableView;
    public TableColumn<Part, Integer> AddProductAssociatedIDColumn;
    public TableColumn<Part, String> AddProductAssociatedNameColumn;
    public TableColumn<Part, Integer> AddProductAssociatedInventoryColumn;
    public TableColumn<Part, Double> AddProductAssociatedPriceColumn;
    public Button AddProductRemovePartButton;

    public Button AddProductSaveButton;
    public Button AddProductCancelButton;
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
    public void AddProductAddPart() {
        Part selected = AddProductPartTableView.getSelectionModel().getSelectedItem();
        workingProduct.addAssociatedPart(selected);
    }

    /**
     * Removes selected part from product associatedParts
     */
    public void AddProductRemovePart() {
        Part selected = AddProductAssociatedPartTableView.getSelectionModel().getSelectedItem();
        for (Part x : workingProduct.getAllAssociatedParts()) {
            if (x.getId() == selected.getId()) {
                workingProduct.deleteAssociatedPart(selected);
                break;
            }
        }
    }

    /**
     * Performs sanity check on the data entered in TextFields, then adds a new product to Inventory if sanity check passes
     */
    public void AddProductSave() {
        boolean sanity = SanityCheck();
        if (sanity) {
            IDCounter++;
            String newName = AddProductNameTextField.getText();
            double newPrice = Double.parseDouble(AddProductPriceTextField.getText());
            int newStock = Integer.parseInt(AddProductInventoryTextField.getText());
            int newMin = Integer.parseInt(AddProductMinTextField.getText());
            int newMax = Integer.parseInt(AddProductMaxTextField.getText());
            workingProduct.setId(IDCounter);
            workingProduct.setName(newName);
            workingProduct.setPrice(newPrice);
            workingProduct.setStock(newStock);
            workingProduct.setMin(newMin);
            workingProduct.setMax(newMax);
            Inventory.addProduct(workingProduct);
            Stage ModifyPartForm = (Stage) AddProductCancelButton.getScene().getWindow();
            ModifyPartForm.close();
        }
    }

    /**
     * Closes Add Product form
     */
    public void AddProductCancel() {
        Stage ModifyPartForm = (Stage) AddProductCancelButton.getScene().getWindow();
        ModifyPartForm.close();
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
            inventory = Integer.parseInt(AddProductInventoryTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            InventoryIntegerLabel.setVisible(true);
        }
        //Check if price field has a double
        try {
            Double.parseDouble(AddProductPriceTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            PriceDoubleLabel.setVisible(true);
        }
        //Check if min field has an integer
        try {
            min = Integer.parseInt(AddProductMinTextField.getText());
        } catch (NumberFormatException e) {
            pass = false;
            MinIntegerLabel.setVisible(true);
        }
        //Check if max field has an integer
        try {
            max = Integer.parseInt(AddProductMaxTextField.getText());
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
     * Searches parts list for text in part search view, then updates parts TableView
     */
    public void AddProductSearch() {
        String search = AddProductSearchTextField.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        try {
            int IDSearch = Integer.parseInt(search);
            if (Inventory.lookupPart(IDSearch) != null)
                searchResults.add(Inventory.lookupPart(IDSearch));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupPart(search);
        }
        AddProductPartTableView.setItems(searchResults);
        if (searchResults.size() == 0)
            AddProductPartTableView.setPlaceholder(new Label("No results matched " + search));
    }

    /**
     * Initializes AddProductPart and AddProductAssociatedPart TableViews
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddProductPartTableView.setItems(Inventory.getAllParts());
        AddProductPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        AddProductAssociatedPartTableView.setItems(workingProduct.getAllAssociatedParts());
        AddProductAssociatedIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddProductAssociatedNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddProductAssociatedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
