package Controller;

import Model.User;
import Service.Session;
import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static database.DatabaseUtil.getconnection;

public class profPolicyController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button closeButton;
    @FXML
    private CheckBox agreeTermsCheckBox;
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
            String email = user.getEmail();


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
//    @FXML
//    public void agreeTermsCheckBoxAction(ActionEvent event) {
//        User user = Session.getCurrentUser();
//        if (user != null) {
//
//
//            boolean agreed = agreeTermsCheckBox.isSelected();
//
//            updateUserAgreementStatus(agreed);
//        }
//    }
//
//    private void updateUserAgreementStatus(boolean agreed) {
//        User user = Session.getCurrentUser();
//        String email = user.getEmail();
//        String sql = "UPDATE users SET terms = ? WHERE email = ?";
//        try (Connection conn = getconnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setBoolean(1, agreed);
//            stmt.setString(2, email);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static boolean hasUserAgreedToTerms(String username) {
//        User user = Session.getCurrentUser();
//        String email = user.getEmail();
//        DatabaseUtil connectnow = new DatabaseUtil();
//        Connection connectdb = getconnection();
//        String sql = "SELECT terms FROM users WHERE email = ?";
//
//
//        try (Connection conn = getconnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getBoolean("terms");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false; // Default to false if an error occurs
//    }
@FXML
public void agreeTermsCheckBoxAction(ActionEvent event) {
    User user = Session.getCurrentUser();
    if (user != null) {
        boolean agreed = agreeTermsCheckBox.isSelected();
        updateUserAgreementStatus(user.getEmail(), agreed);
    }
}

    private void updateUserAgreementStatus(String email, boolean agreed) {
        String sql = "UPDATE users SET terms = ? WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, agreed);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasUserAgreedToTerms(String email) {
        String sql = "SELECT terms FROM users WHERE email = ?";
        try (Connection conn = getConnection();
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

    // Assuming you have a method to get the database connection
    private static Connection getConnection() throws SQLException {
        // Implement your connection logic here
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/knk2024", "root", "");
    }

}
