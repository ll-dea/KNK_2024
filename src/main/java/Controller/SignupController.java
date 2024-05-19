package Controller;

import Main.Main;
import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class SignupController {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private Button goToLoginButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField signupEmailField;
    @FXML
    private PasswordField signupPasswordField;
    @FXML
    private PasswordField repeatSignupPasswordField;
    @FXML
    private Label signupValidateLabel;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void goToLoginButtonAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signupButtonAction(ActionEvent e) {
        if (!firstNameField.getText().isBlank() && !secondNameField.getText().isBlank() && !signupEmailField.getText().isBlank() && !signupPasswordField.getText().isBlank() && !repeatSignupPasswordField.getText().isBlank()) {
            if (signupEmailField.getText().endsWith("@student.com")) {
                if (signupPasswordField.getText().equals(repeatSignupPasswordField.getText())) {
                    checkEmailAndSaveCredentials();
                } else {
                    signupValidateLabel.setText("Passwords don't match");
                }
            } else {
                signupValidateLabel.setText("Use student's email!");
            }
        } else {
            signupValidateLabel.setText("You need to enter all the fields!");
        }
    }

    private void checkEmailAndSaveCredentials() {
        DatabaseUtil connectNow = new DatabaseUtil();
        Connection connectdb = connectNow.getconnection();

        String checkEmailQuery = "SELECT count(1) FROM users WHERE email = ?";
        try {
            PreparedStatement checkEmailStatement = connectdb.prepareStatement(checkEmailQuery);
            checkEmailStatement.setString(1, signupEmailField.getText());
            ResultSet emailResultSet = checkEmailStatement.executeQuery();

            if (emailResultSet.next() && emailResultSet.getInt(1) == 0) {
                saveCredentials(connectdb);
            } else {
                signupValidateLabel.setText("This email is already in use!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            signupValidateLabel.setText("An error occurred. Please try again later.");
        }
    }

    private void saveCredentials(Connection connectdb) {
        String insertFields = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connectdb.prepareStatement(insertFields);
            preparedStatement.setString(1, firstNameField.getText());
            preparedStatement.setString(2, secondNameField.getText());
            preparedStatement.setString(3, signupEmailField.getText());
            preparedStatement.setString(4, hashPassword(signupPasswordField.getText()));

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                signupValidateLabel.setText("You have been signed up successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            signupValidateLabel.setText("An error occurred. Please try again later.");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
