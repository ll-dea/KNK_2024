//package Controller;
//
//import Main.Main;
//import database.DatabaseUtil;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//import javafx.scene.control.TextField;
//import javafx.scene.control.PasswordField;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class loginController {
//
//    private Parent root;
//    private Stage stage;
//    private Scene scene;
//
//    @FXML
//    private TextField usernameTextField;
//    @FXML
//    private PasswordField passwordPasswordField;
//    @FXML
//    private Button closeButton;
//    @FXML
//    private Label loginMessageLabel;
//
//    public void cancelButtonOnAction(ActionEvent e){
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }
//    public void loginButtonAction(ActionEvent e) throws IOException {
//
//        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
//            validatelogin();
//
//        }
//        else{
//            loginMessageLabel.setText("You need to enter your username and password!");
//        }
//    }
//    @FXML
//    private Button goToSignupButton;
//
//    public void goToSignupButtonAction(ActionEvent e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/Signup.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//
//
//    public void validatelogin(){
//        DatabaseUtil connectNow = new DatabaseUtil();
//        Connection connectdb = connectNow.getconnection();
//
//        String verifyLogin = "SELECT count(1) from users where email = '"+usernameTextField.getText()+"' AND password = '"+passwordPasswordField.getText()+"'";
//        try {
//            Statement statement = connectdb.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifyLogin);
//
//            while (queryResult.next()){
//                if (queryResult.getInt(1) == 1){
//
//
//                }else{
//                    loginMessageLabel.setText("Invalid login. Please try again!");
//
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//}

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
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button closeButton;
    @FXML
    private Label loginMessageLabel;

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent e) throws IOException {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validatelogin(e); // Pass the event to the validation method
        } else {
            loginMessageLabel.setText("You need to enter your username and password!");
        }
    }

    @FXML
    private Button goToSignupButton;

    public void goToSignupButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/Signup.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void validatelogin(ActionEvent e) {
        DatabaseUtil connectNow = new DatabaseUtil();
        Connection connectdb = connectNow.getconnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next() && queryResult.getInt(1) == 1) {
                // Load the new FXML file and set the scene
                Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/home.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                loginMessageLabel.setText("Invalid login. Please try again!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            loginMessageLabel.setText("An error occurred. Please try again later.");
        }
    }
}
