package Controller;

import Model.User;
import Service.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class policyController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;

    public policyController() {
        this.stage = new Stage();
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void goToProfileButtonAction(ActionEvent e) throws IOException {
        initializeStage();
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/userProfile.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToPasswordeButtonAction(ActionEvent e) throws IOException {
        initializeStage();
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/userPassword.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToPolicyButtonAction(ActionEvent e) throws IOException {
        initializeStage();
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/userPolicy.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeStage() {
        if (stage.getStyle() != StageStyle.UNDECORATED) {
            stage.initStyle(StageStyle.UNDECORATED);
        }
    }
    @FXML
    private Label emri_mbiemriLabel;
    @FXML
    public void initialize() {
        System.out.println("Initialize method called in profProfileController");

        User user = Session.getCurrentUser();
        if (user != null) {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();

            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);

            if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
                emri_mbiemriLabel.setText(firstName + " " + lastName);
            } else {
                emri_mbiemriLabel.setText("Unknown");
            }
        } else {
            emri_mbiemriLabel.setText("Unknown");
        }
    }
}
