package Controller;

import Main.Main;
import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController  {

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
            validatelogin(e);
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

        String verifyLogin = "SELECT password FROM users WHERE email = '" + usernameTextField.getText() + "'";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                String retrievedHashedPassword = queryResult.getString("password");
                String enteredPasswordHash = hashPassword(passwordPasswordField.getText());

                if (retrievedHashedPassword.equals(enteredPasswordHash)) {
                    // Check the email domain
                    String email = usernameTextField.getText();
                    String fxmlFile = "";
                    if (email.endsWith("@student.com")) {
                        fxmlFile = "/sceneBuilderFiles/home.fxml";
                    } else if (email.endsWith("@professor.com")) {
                        fxmlFile = "/sceneBuilderFiles/profhome.fxml";
                    }

                    if (!fxmlFile.isEmpty()) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
                            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            loginMessageLabel.setText("Cannot log in. Try again later!");
                        }
                    } else {
                        loginMessageLabel.setText("Invalid email domain!");
                    }
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                }
            } else {
                loginMessageLabel.setText("Invalid login. Please try again!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            loginMessageLabel.setText("An error occurred. Please try again later.");
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
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Locale locale = Locale.getDefault();
//        ResourceBundle bundle = ResourceBundle.getBundle(
//                "translations", locale
//        );
//        this.goToSignupButton.setText(bundle.getString("lblCreateNewAccount"));
//    }
}
