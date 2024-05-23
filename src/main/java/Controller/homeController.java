package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;




public class homeController {

    private Parent root;
    private Stage stage;
    private Scene scene;




    @FXML
    private Button closeButton; // This should match the fx:id in the FXML file

    @FXML
    private MenuItem profileMenuItem;
    @FXML
    private MenuItem passwordMenuItem;
    @FXML
    private MenuItem termsMenuItem;

    @FXML
    public void initialize() {
        // Set up event handlers for menu items
        profileMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userProfile.fxml"));
        passwordMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userPassword.fxml"));
        termsMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userPolicy.fxml"));
    }

    private void openNewWindow(String fxmlPath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Create a new stage for the new window
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));

            // Show the new window
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly
        }
    }




    public void signoutButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }



}
