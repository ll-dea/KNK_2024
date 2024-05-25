package Controller;

import Model.User;
import Service.Session;
import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DatabaseUtil.getconnection;

public class policyController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;
    @FXML
    private CheckBox agreeTermsCheckBox;

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
    @FXML
    public void agreeTermsCheckBoxAction(ActionEvent event) {
        System.out.println("Funksion agree TermsCheckBoxAction eshte thirr!");
        User user = Session.getCurrentUser();
        if (user != null) {


            boolean agreed = agreeTermsCheckBox.isSelected();

            updateUserAgreementStatus(agreed);
            hasUserAgreedToTerms(String.valueOf(agreed));
        }
    }

    private void updateUserAgreementStatus(boolean agreed) {
        System.out.println("Funksion agree update eshte thirr!");

        User user = Session.getCurrentUser();
        String email = user.getEmail();
        String sql = "UPDATE users SET terms = ? WHERE email = ?";

        try (Connection conn = getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, agreed);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // Method to check if the user has already agreed to the terms
    public static boolean hasUserAgreedToTerms(String username) {
        System.out.println("Po shikohet nese ka agree!");

        User user = Session.getCurrentUser();
        String email = user.getEmail();
        DatabaseUtil connectnow = new DatabaseUtil();
        Connection connectdb = getconnection();
        String sql = "SELECT terms FROM users WHERE email = ?";


        try (Connection conn = getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("terms");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }
}
