package Controller;

import Model.User;
import Service.PasswordService;
import Service.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class profPasswordController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordService passwordService;

    @FXML
    private PasswordField currentPasswordPasswordField;

    @FXML
    private PasswordField newPasswordPasswordField;

    @FXML
    private PasswordField repeatPasswordPasswordField;

    @FXML
    private Button ruajButton;

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
        passwordService = new PasswordService();

    }


    @FXML
    void changePassword(ActionEvent event) {
        String currentPassword = currentPasswordPasswordField.getText();
        String newPassword = newPasswordPasswordField.getText();
        String repeatPassword = repeatPasswordPasswordField.getText();

        boolean passwordChanged = passwordService.changePassword(currentPassword, newPassword, repeatPassword);
        if (passwordChanged) {
            showAlert("Password changed successfully.", AlertType.INFORMATION);
            clearFields();
        } else {
            showAlert("Failed to change password. Please check your input.", AlertType.ERROR);
        }
    }

    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        currentPasswordPasswordField.clear();
        newPasswordPasswordField.clear();
        repeatPasswordPasswordField.clear();
    }


    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private Label emri_mbiemriLabel;


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
