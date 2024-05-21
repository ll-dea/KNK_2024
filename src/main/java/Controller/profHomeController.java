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

    @FXML
    private TextField idLenda;
    @FXML
    private DatePicker idDataKonsulltimeve;
    @FXML
    private TextField oraKonsulltimeve;
    @FXML
    private TextField sallaKonsulltiemve;

    private Sesioni sesioni;

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
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Orari u shtua me sukses!");
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



}

