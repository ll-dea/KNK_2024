//package Controller;
//
//import Main.Main;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class SignupController {
//    private Parent root;
//    private Scene scene;
//    private Stage stage;
//    @FXML
//    private Button goToLoginButton;
//    @FXML
//    private Button closeButton;
//    @FXML
//    private TextField firstNameField;
//    @FXML
//    private TextField secondNameField;
//    @FXML
//    private TextField signupEmailField;
//    @FXML
//    private PasswordField signupPasswordField;
//    @FXML
//    private PasswordField repeatSignupPasswordField;
//    @FXML
//    private Label signupValidateLabel;
//
//
//    public void cancelButtonOnAction(ActionEvent e){
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }
//
//    public void goToLoginButtonAction(ActionEvent e) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void signupButtonAction(ActionEvent e) {
//
//        if (firstNameField.getText().isBlank() == false && secondNameField.getText().isBlank() == false && signupEmailField.getText().isBlank() == false && signupPasswordField.getText().isBlank() == false && repeatSignupPasswordField.getText().isBlank() == false) {
//            if(signupPasswordField == repeatSignupPasswordField){
//                signupValidateLabel.setText("You have been signed up");
//            }else {
//                signupValidateLabel.setText("Passwords don't match");
//            }
//        }
//        else{
//            signupValidateLabel.setText("You need to enter your username and password!");
//        }
//    }
//}
//

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            if (signupPasswordField.getText().equals(repeatSignupPasswordField.getText())) {
                saveCredentials();
            } else {
                signupValidateLabel.setText("Passwords don't match");
            }
        } else {
            signupValidateLabel.setText("You need to enter all the fields!");
        }
    }

    private void saveCredentials() {
        DatabaseUtil connectNow = new DatabaseUtil();
        Connection connectdb = connectNow.getconnection();

        String insertFields = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connectdb.prepareStatement(insertFields);
            preparedStatement.setString(1, firstNameField.getText());
            preparedStatement.setString(2, secondNameField.getText());
            preparedStatement.setString(3, signupEmailField.getText());
            preparedStatement.setString(4, signupPasswordField.getText());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                signupValidateLabel.setText("You have been signed up successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            signupValidateLabel.setText("An error occurred. Please try again later.");
        }
    }
}

