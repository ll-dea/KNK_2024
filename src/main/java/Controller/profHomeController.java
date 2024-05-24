package Controller;


import Model.OrariKonsultimeve;
import Service.Session;
import database.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import Model.User;



public class profHomeController {

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
    private Button signoutButton;


    public void signoutButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

        initializeTableView();


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
    @FXML
    private TableView<Model.OrariKonsultimeve> OrariKonsultimeve;
    @FXML
    private TableColumn<OrariKonsultimeve, Integer> Id;
    @FXML
    private TableColumn<OrariKonsultimeve, String> Lenda;

    @FXML
    private TableColumn<OrariKonsultimeve, Date> Data;
    @FXML
    private TableColumn<OrariKonsultimeve, String> Ora;
    @FXML
    private TableColumn<OrariKonsultimeve, String> Salla;
    @FXML
    private ObservableList<OrariKonsultimeve> List = FXCollections.observableArrayList();


    private void loadDataFromDatabase() {

        User user = Session.getCurrentUser();
        if (user != null) {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            DatabaseUtil connector = new DatabaseUtil();
            Connection connection = connector.getconnection();
            try {
                List.clear(); // Clear the list before loading new data

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Id, Lenda , Data , Ora, Salla FROM lendet where prof = '" + firstName + " " + lastName + "'");

                while (resultSet.next()) {
                    int index = resultSet.getInt("Id");
                    String lenda = resultSet.getString("Lenda");
                    Date data = resultSet.getDate("Data");
                    String ora = resultSet.getString("Ora");
                    String salla = resultSet.getString("Salla");

                    List.add(new OrariKonsultimeve(index, lenda, data, ora, salla));
                }

                OrariKonsultimeve.setItems(List);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
    private void initializeTableView() {
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Lenda.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        Data.setCellValueFactory(new PropertyValueFactory<>("data"));
        Ora.setCellValueFactory(new PropertyValueFactory<>("ora"));
        Salla.setCellValueFactory(new PropertyValueFactory<>("salla"));

        loadDataFromDatabase();
    }
    @FXML
    private TextField Lend;
    @FXML
    private DatePicker idDataKonsulltimeve;
    @FXML
    private TextField oraKonsulltimeve;
    @FXML
    private TextField sallaKonsulltiemve;

    @FXML
    private void addLenda() {
        // Get user inputs
        String lend = Lend.getText();
        LocalDate data = idDataKonsulltimeve.getValue();
        String ora = oraKonsulltimeve.getText();
        String salla = sallaKonsulltiemve.getText();

        String prof = Session.getCurrentUser().getFirstName() + " " + Session.getCurrentUser().getLastName();

        String sql = "INSERT INTO lendet (Lenda, Prof, Data, Ora, Salla) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getconnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameters
            statement.setString(1, lend);
            statement.setString(2, prof);
            statement.setDate(3, java.sql.Date.valueOf(data));
            statement.setString(4, ora);
            statement.setString(5, salla);

            // Execute the statement
            statement.executeUpdate();

            // Clear input fields after adding the entry
            clearInputFields();

            // Optionally, you can reload the table to reflect the changes
            loadDataFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
        }
    }

    // Method to clear input fields after adding an entry
    private void clearInputFields() {
        Lend.clear();
        idDataKonsulltimeve.setValue(null);
        oraKonsulltimeve.clear();
        sallaKonsulltiemve.clear();
    }

}

