package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Sami Siddiqui
 */
public class MainController implements Initializable {

    public TableView<Part> PartsTableView;
    public TableColumn<Part, Integer> PartsIDColumn;
    public TableColumn<Part, String> PartsNameColumn;
    public TableColumn<Part, Integer> PartsStockColumn;
    public TableColumn<Part, Double> PartsCostColumn;
    public Label PartDeleteFailureLabel;

    public TextField PartSearchTextField;
    public TextField ProductSearchTextField;
    public TableView<Product> ProductsTableView;
    public TableColumn<Part, Integer> ProductsIDColumn;
    public TableColumn<Part, String> ProductsNameColumn;
    public TableColumn<Part, Integer> ProductsStockColumn;
    public TableColumn<Part, Double> ProductsPriceColumn;

    public Button DeletePartButton;
    public Button ModifyPartButton;
    public Button AddPartButton;

    public Button DeleteProductButton;
    public Button ModifyProductButton;
    public Button AddProductButton;

    public Button ExitButton;
    public Button PartConfirmDeleteButton;
    public Button ProductConfirmDeleteButton;

    /**
     * Creates Add Part form
     */
    public void CallAddPartForm() throws IOException {
        Stage AddPartForm = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPartForm.fxml")));
        AddPartForm.setTitle("Add Part");
        AddPartForm.setScene(new Scene(root, 600, 400));
        AddPartForm.show();
        AddPartForm.setResizable(false);
    }

    /**
     * Creates Modify Part form
     */
    public void CallModifyPartForm() throws IOException {
        Part selected = PartsTableView.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;
        Stage ModifyPartForm = new Stage();
        FXMLLoader z = new FXMLLoader(getClass().getResource("ModifyPartForm.fxml"));
        Parent root = z.load();
        ModifyPartController x = z.getController();

        x.ModifyPartIDTextField.setText(String.valueOf(selected.getId()));
        x.ModifyPartNameTextField.setText(selected.getName());
        x.ModifyPartInventoryTextField.setText(String.valueOf(selected.getStock()));
        x.ModifyPartPriceTextField.setText(String.valueOf(selected.getPrice()));
        x.ModifyPartMinTextField.setText(String.valueOf(selected.getMin()));
        x.ModifyPartMaxTextField.setText(String.valueOf(selected.getMax()));

        //Ensures that whether selected is InHouse or Outsourced, Modify Part page has correct label and toggle switch selected
        if (selected instanceof InHouse) {
            x.ModifyPartToggleTextField.setText(String.valueOf(((InHouse) selected).getMachineID()));
        } else {
            x.ModifyPartToggleTextField.setText(((Outsourced) selected).getCompanyName());
            x.ModifyPartToggleLabel.setText("Company Name");
            x.ModifyPartOutsourced.setSelected(true);
        }
        ModifyPartForm.setTitle("Modify Part");
        ModifyPartForm.setScene(new Scene(root, 600, 400));
        ModifyPartForm.show();
        ModifyPartForm.setResizable(false);
    }

    /**
     * Sets the part delete confirmation button visible
     */
    public void DeletePart() {
        Part selected = PartsTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PartConfirmDeleteButton.setVisible(true);
        }
    }

    /**
     * Deletes selected part and hides part delete confirm button
     */
    public void PartConfirmDelete() {
        Part selected = PartsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selected);
        PartConfirmDeleteButton.setVisible(false);
        PartsTableView.setItems(Inventory.getAllParts());
    }

    /**
     * Searches parts list for text in part search view, then updates parts TableView
     */
    public void PartSearch() {
        String search = PartSearchTextField.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        //performs an ID search if search bar contains an integer, or a string search otherwise
        try {
            int IDSearch = Integer.parseInt(search);
            if (Inventory.lookupPart(IDSearch) != null)
                searchResults.add(Inventory.lookupPart(IDSearch));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupPart(search);
        }
        PartsTableView.setItems(searchResults);
        if (searchResults.size() == 0)
            PartsTableView.setPlaceholder(new Label("No results matched " + search));
    }

    /**
     * Calls Add Product form
     */
    public void CallAddProductForm() throws IOException {
        Stage AddProductForm = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProduct.fxml")));
        AddProductForm.setTitle("Add Product");
        AddProductForm.setScene(new Scene(root, 800, 600));
        AddProductForm.show();
        AddProductForm.setResizable(false);
    }

    /**
     * Calls Modify Product form
     */
    public void CallModifyProductForm() throws IOException {
        Product selected = ProductsTableView.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;
        Stage ModifyProductForm = new Stage();
        FXMLLoader z = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        Parent root = z.load();
        ModifyProductController x = z.getController();
        ModifyProductForm.setTitle("Modify Product");
        x.PassProductFromMain(selected);
        ModifyProductForm.setScene(new Scene(root, 800, 600));
        ModifyProductForm.show();
        ModifyProductForm.setResizable(false);
    }

    /**
     * Sets the products delete confirmation button visible
     */
    public void DeleteProduct() {
        Product selected = ProductsTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ProductConfirmDeleteButton.setVisible(true);
        }
    }

    /**
     * Deletes selected product and hides product delete confirm button
     */
    public void ProductConfirmDelete() {
        Product selected = ProductsTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(selected);
        ProductConfirmDeleteButton.setVisible(false);
        ProductsTableView.setItems(Inventory.getAllProducts());
    }

    /**
     * Searches products list for text in product search view, then updates products TableView
     */
    public void ProductSearch() {
        String search = ProductSearchTextField.getText();
        ObservableList<Product> searchResults = FXCollections.observableArrayList();
        try {
            int IDSearch = Integer.parseInt(search);
            if (Inventory.lookupProduct(IDSearch) != null)
                searchResults.add(Inventory.lookupProduct(IDSearch));
        } catch (NumberFormatException e) {
            searchResults = Inventory.lookupProduct(search);
        }
        ProductsTableView.setItems(searchResults);
        if (searchResults.size() == 0)
            ProductsTableView.setPlaceholder(new Label("No results matched " + search));
    }

    /**
     * Exits program
     */
    public void ExitProgram() {
        Platform.exit();
    }

    /**
     * Initializes parts and products TableViews
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InHouse n = new InHouse(1, "InHouse", 2.99, 10, 1, 15, 20);
        Outsourced q = new Outsourced(2, "Outsourced", 15.99, 30, 1, 150, "Greasy Sal's");
        InHouse r = new InHouse(3, "Meat", 2.99, 10, 1, 15, 20);
        Inventory.addPart(n);
        Inventory.addPart(q);
        Inventory.addPart(r);
        Product a = new Product(1,"Grass",4.99,10,1,30);
        Product b = new Product(2, "Loss", 2.50, 4, 1, 100);
        Product c = new Product(3, "Grease", 11.99, 25, 1, 50);
        Inventory.addProduct(a);
        Inventory.addProduct(b);
        Inventory.addProduct(c);

        PartsTableView.setItems(Inventory.getAllParts());
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTableView.setItems(Inventory.getAllProducts());
        ProductsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }
}
