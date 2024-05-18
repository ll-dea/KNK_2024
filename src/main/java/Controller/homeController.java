package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;

    @FXML
    private Button signoutButton;

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
