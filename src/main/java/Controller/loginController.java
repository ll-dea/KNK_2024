package Controller;

import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Model.User;
import Service.Session;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private ResourceBundle resourceBundle;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button closeButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button languageButton;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent e) throws IOException {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validatelogin(e);
            loginButton.requestFocus();
        } else {
            loginMessageLabel.setText("You need to enter your username and password!");
        }
    }

    @FXML
    private Button goToSignupButton;

    public void goToSignupButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/Signup.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void validatelogin(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Node) {
            Node nodeSource = (Node) source;
            stage = (Stage) nodeSource.getScene().getWindow();
        }
        DatabaseUtil connectNow = new DatabaseUtil();
        try (Connection connectdb = connectNow.getconnection()) {
            String email = usernameTextField.getText();
            String verifyLogin = "SELECT id, first_name, last_name, email, password, gjinia, terms FROM users WHERE email = ?";

            try (PreparedStatement statement = connectdb.prepareStatement(verifyLogin)) {
                statement.setString(1, email);
                ResultSet queryResult = statement.executeQuery();

                if (queryResult.next()) {
                    String retrievedHashedPassword = queryResult.getString("password");
                    String enteredPasswordHash = hashPassword(passwordPasswordField.getText());

                    if (retrievedHashedPassword.equals(enteredPasswordHash)) {
                        int id = queryResult.getInt("id");
                        String firstName = queryResult.getString("first_name");
                        String lastName = queryResult.getString("last_name");
                        String gjinia = queryResult.getString("gjinia");
                        String terms = queryResult.getString("terms");

                        User user = new User(id, firstName, lastName, email, retrievedHashedPassword, gjinia, terms);
                        Session.login(user);

                        String fxmlFile = email.endsWith("@student.uni-pr.edu") ? "/sceneBuilderFiles/home.fxml" : "/sceneBuilderFiles/profHome.fxml";

                        if (!fxmlFile.isEmpty()) {
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
                                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                                loginMessageLabel.setText("Cannot load the home page. Please try again later!");
                            }
                        } else {
                            loginMessageLabel.setText("Invalid email domain!");
                        }
                    } else {
                        loginMessageLabel.setText("Incorrect password. Please try again!");
                    }
                } else {
                    loginMessageLabel.setText("User with this email does not exist!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            loginMessageLabel.setText("An error occurred while logging in. Please try again later.");
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

    private static loginController instance;


    public void initialize() {
        resourceBundle = ResourceBundle.getBundle("content_en", Locale.getDefault());


        usernameTextField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    loginButtonAction(new ActionEvent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        passwordPasswordField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    loginButtonAction(new ActionEvent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeLanguage(ActionEvent event) {
        Locale currentLocale = resourceBundle.getLocale();
        if (currentLocale.getLanguage().equals("en")) {
            resourceBundle = ResourceBundle.getBundle("content_sq", Locale.getDefault());
        } else {
            resourceBundle = ResourceBundle.getBundle("content_en", Locale.getDefault());
        }

        loginMessageLabel.setText(resourceBundle.getString("invalidEmailDomainMessage"));
        usernameTextField.setPromptText(resourceBundle.getString("textEmriApp"));
        passwordPasswordField.setPromptText(resourceBundle.getString("loginPasswordPasswordField"));
        loginButton.setText(resourceBundle.getString("loginButton"));
        closeButton.setText(resourceBundle.getString("closeButton"));
        languageButton.setText(resourceBundle.getString("languageButton"));
        loginMessageLabel.setText(resourceBundle.getString("invalidEmailDomainMessage"));
        loginMessageLabel.setText(resourceBundle.getString("incorrectPasswordMessage"));
        loginMessageLabel.setText(resourceBundle.getString("userDoesNotExistMessage"));
        loginMessageLabel.setText(resourceBundle.getString("loginErrorMessage"));
        goToSignupButton.setText(resourceBundle.getString("goToSignupButton"));


    }
}





