package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *
 * @author Sami Siddiqui
 * <p>Logical error: When doing a search using any of the searchbars, searching for a number that didn't correspond with an ID created an error where the ObservableList used to show the filtered search results had a non-zero size, but nothing added to it. This caused the associated TableView to show an empty table instead of showing the placeholder text. The issue was resolved by implementing a check that the part returned by Inventory.lookupPart() is not null. If the part is not null, the part was added to the ObservableList.</p>
 * <p>Future enhancement: A key update would be to implement data permanence by writing data to an external file.</p>
 * <p>Javadocs location: In folder Javadocs</p>
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1200, 310));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
