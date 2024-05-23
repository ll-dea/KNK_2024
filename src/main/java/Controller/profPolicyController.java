package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class profPolicyController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void goToProfileButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/profProfile.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToPasswordeButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/profPassword.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToPolicyButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/profPolicy.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToSatisticsButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/profSatistics.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
