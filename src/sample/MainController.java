package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView<Part> PartsTableView;
    public TableColumn PartsIDColumn;
    public TableColumn PartsNameColumn;
    public TableColumn PartsStockColumn;
    public TableColumn PartsCostColumn;
    public Label PartDeleteFailureLabel;


    private final ObservableList<Part> allParts = FXCollections.observableArrayList();
    public TextField PartSearchTextField;
    //private ObservableList<Product> productList = FXCollections.observableArrayList();



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
        Part selected = PartsTableView.getSelectionModel().getSelectedItem();
        Stage ModifyPartForm = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("ModifyPartForm.fxml"));
        FXMLLoader z = new FXMLLoader(getClass().getResource("ModifyPartForm.fxml"));
        Parent root = z.load();
        ModifyPartController x = z.getController();

        x.ModifyPartIDTextField.setText(String.valueOf(selected.getId()));
        x.ModifyPartNameTextField.setText(selected.getName());
        x.ModifyPartInventoryTextField.setText(String.valueOf(selected.getStock()));
        x.ModifyPartPriceTextField.setText(String.valueOf(selected.getPrice()));
        x.ModifyPartMinTextField.setText(String.valueOf(selected.getMin()));
        x.ModifyPartMaxTextField.setText(String.valueOf(selected.getMax()));

        if (selected instanceof InHouse) {
            x.ModifyPartToggleTextField.setText(String.valueOf(((InHouse) selected).getMachineID()));
        } else {
            x.ModifyPartToggleTextField.setText(((Outsourced) selected).getCompanyName());
            x.ModifyPartOutsourced.setSelected(true);
        }
        ModifyPartForm.setTitle("Add Part");
        ModifyPartForm.setScene(new Scene(root, 600, 400));
        ModifyPartForm.show();
        ModifyPartForm.setResizable(false);
    }

    public void DeletePart(ActionEvent actionEvent) {
        Part selected = PartsTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            PartDeleteFailureLabel.setText("No part deleted");
        } else {
            Inventory.deletePart(selected);
            PartDeleteFailureLabel.setText("Part deleted");
        }
        PartDeleteFailureLabel.setVisible(true);
    }

    public void PartSearch(ActionEvent actionEvent) {
        String search = PartSearchTextField.getText();
        ObservableList<Part> q = Inventory.lookupPart(search);
        PartsTableView.setItems(q);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InHouse n = new InHouse(7, "InHouse", 2.99, 10, 1, 15, 20);
        Outsourced q = new Outsourced(7, "Outsourced", 2.99, 10, 1, 15, "20");
        InHouse r = new InHouse(7, "Meat", 2.99, 10, 1, 15, 20);

        Inventory newInventory = new Inventory();
        Inventory.addPart(q);
        Inventory.addPart(n);
        Inventory.addPart(r);
        PartsTableView.setItems(Inventory.getAllParts());

        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
