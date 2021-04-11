package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private final ObservableList<Part> allParts = FXCollections.observableArrayList();
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
        Stage ModifyPartForm = new Stage();
        Stage MainForm = (Stage) ExitButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ModifyPartForm.fxml"));
        ModifyPartForm.setTitle("Add Part");
        ModifyPartForm.setScene(new Scene(root, 600, 400));
        ModifyPartForm.show();
        ModifyPartForm.setResizable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InHouse n = new InHouse(1, "2", 3,4,5,6,7);
        Inventory x = new Inventory();
        x.addPart(n);
        PartsTableView.setItems(allParts);

        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        allParts.add(new InHouse(7, "Grease", 2.99, 10, 1, 15, 20));
        allParts.add(new Outsourced(70, "Zagoo", 2.99, 10, 1, 15, "20"));
    }
}
