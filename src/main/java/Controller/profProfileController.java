package Controller;

import Service.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Model.User;

import java.io.IOException;

public class profProfileController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label emri_mbiemriLabel;
    @FXML
    private Label emriLabel;
    @FXML
    private Label mbiemriLabel;
    @FXML
    private Label emailLabel;

    @FXML
    public void initialize() {

        User user = Session.getCurrentUser();
        if (user != null) {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();


            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);

            if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
                emri_mbiemriLabel.setText(firstName + " " + lastName);
                emriLabel.setText(firstName);
                mbiemriLabel.setText(lastName);
                emailLabel.setText(email);



            } else {
                emri_mbiemriLabel.setText("Unknown");
            }
        } else {
            emri_mbiemriLabel.setText("Unknown");
        }
    }

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
    public void shfaqTeDhenat(){

    }
}
