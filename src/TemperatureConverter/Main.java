package TemperatureConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(); // Create the FXMLLoader

        // Path to the FXML File
        // String fxmlDocPath = "src/sample/sample.fxml"; // Check here if you get a
        // Putting the full path like below will enable to run your JFXaplication jar
        // file at your OS commmand line.
        String fxmlDocPath = "./src/TemperatureConverter/sample.fxml"; // Check here if you get a
        // FileNotFound exception

        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath); // file input
        // stream object to read the sample.fxml file

        // Create the Pane, root and all Details
        AnchorPane root = loader.load(fxmlStream); // Create the pane by simply applying
        // the info in sample.fxml

        // Create the Scene
        Scene scene = new Scene(root); // Pass the Pane, root above to create the scene

        stage.setScene(scene); // Set the Scene to the scene

        stage.setTitle("Glacier Temperature Unit Converter"); // Set the Title to the Stage

        stage.show();// Display the Stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
