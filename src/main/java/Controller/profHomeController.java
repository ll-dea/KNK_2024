package Controller;

import Model.OrariKonsultimeve;
import Service.Sesioni;
import Main.Main;
import database.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;



public class profHomeController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;


    @FXML
    private Button closeButton;
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TextField idLenda;
    @FXML
    private DatePicker idDataKonsulltimeve;
    @FXML
    private TextField oraKonsulltimeve;
    @FXML
    private TextField sallaKonsulltiemve;
    @FXML
    private Button signoutButton;

    private Sesioni sesioni;

    public void signoutButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sesioni = new Sesioni();
    }

    @FXML
    private void shtoOrar(ActionEvent event) {
        String lenda = idLenda.getText();
        LocalDate data = idDataKonsulltimeve.getValue();
        String ora = oraKonsulltimeve.getText();
        String salla = sallaKonsulltiemve.getText();

        if (lenda.isEmpty() || data == null || ora.isEmpty() || salla.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Ju lutemi plotësoni të gjitha fushat!");
            return;
        }

        OrariKonsultimeve orari = new OrariKonsultimeve(lenda, data, ora, salla);
        sesioni.shtoOrar(orari);
        sesioni.fshijOrar(orari);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u shtua me sukses!");
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u fshi me sukses!");
    }

    private void clearFields() {
        idLenda.clear();
        idDataKonsulltimeve.setValue(null);
        oraKonsulltimeve.clear();
        sallaKonsulltiemve.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private MenuItem profileMenuItem;
    @FXML
    private MenuItem passwordMenuItem;
    @FXML
    private MenuItem termsMenuItem;
    @FXML
    private MenuItem satisticsMenuItem;

    @FXML
    public void initialize() {
        // Set up event handlers for menu items
        profileMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/profProfile.fxml"));
        passwordMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/profPassword.fxml"));
        termsMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/profPolicy.fxml"));
        satisticsMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/profSatistics.fxml"));

    }

    private void openNewWindow(String fxmlPath) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Create a new stage for the new window
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));

            // Show the new window
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly
        }
    }
    public void viewNotifications() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sceneBuilderFiles/Notifications.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Notifications");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

