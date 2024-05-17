package Controller;

import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {

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
    public void loginButtonAction(ActionEvent e) {

        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
//            loginMessageLabel.setText("You try to login!");
            validatelogin();
        }
        else{
            loginMessageLabel.setText("You need to enter your username and password!");
        }
    }

    public void validatelogin(){
        DatabaseUtil connectNow = new DatabaseUtil();
        Connection connectdb = connectNow.getconnection();

        String verifyLogin = "SELECT count(1) from users where email = '"+usernameTextField.getText()+"' AND password = '"+passwordPasswordField.getText()+"'";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome");

                }else{
                    loginMessageLabel.setText("Invalid login. Please try again!");

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}