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
import javafx.scene.control.*;
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



public class profHomeController {

    @FXML
    private TextField idLenda;
    @FXML
    private DatePicker idDataKonsulltimeve;
    @FXML
    private TextField oraKonsulltimeve;
    @FXML
    private TextField sallaKonsulltiemve;


}
