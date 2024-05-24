package Controller;

import Model.Lendet;
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

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class homeController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton; // This should match the fx:id in the FXML file

    @FXML
    private MenuItem profileMenuItem;
    @FXML
    private MenuItem passwordMenuItem;
    @FXML
    private MenuItem termsMenuItem;

    @FXML
    private TableView<Model.Lendet> Lendet;
    @FXML
    private TableColumn<Lendet, Integer> Index;
    @FXML
    private TableColumn<Lendet, String> Lenda;
    @FXML
    private TableColumn<Lendet, String> Prof;
    @FXML
    private TableColumn<Lendet, Date> Data;
    @FXML
    private TableColumn<Lendet, String> Ora;
    @FXML
    private TableColumn<Lendet, String> Salla;

    @FXML
    private TextField lendaTextField;
    @FXML
    private DatePicker dataDatePicker;

    private ObservableList<Lendet> personList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // MenuItem actions
        profileMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userProfile.fxml"));
        passwordMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userPassword.fxml"));
        termsMenuItem.setOnAction(event -> openNewWindow("/sceneBuilderFiles/userPolicy.fxml"));

        // Initialize TableView
        Index.setCellValueFactory(new PropertyValueFactory<>("id"));
        Lenda.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        Prof.setCellValueFactory(new PropertyValueFactory<>("prof"));
        Data.setCellValueFactory(new PropertyValueFactory<>("data"));
        Ora.setCellValueFactory(new PropertyValueFactory<>("ora"));
        Salla.setCellValueFactory(new PropertyValueFactory<>("salla"));

        loadDataFromDatabase();

        // Add listeners for filtering
        lendaTextField.textProperty().addListener((observable, oldValue, newValue) -> filterData());
        dataDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> filterData());
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


    public void openSuggestionForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sceneBuilderFiles/SuggestionForm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Suggestion Form");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signoutButtonAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/login.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void loadDataFromDatabase() {
        DatabaseUtil connector = new DatabaseUtil();
        Connection connection = connector.getconnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Id, Lenda , Prof, Data , Ora, Salla FROM lendet");

            while (resultSet.next()) {
                int index = resultSet.getInt("Id");
                String lenda = resultSet.getString("Lenda");
                String prof = resultSet.getString("Prof");
                Date data = resultSet.getDate("Data");
                String ora = resultSet.getString("Ora");
                String salla = resultSet.getString("Salla");

                personList.add(new Lendet(index, lenda, prof, data, ora, salla));
            }

            Lendet.setItems(personList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void filterData() {
        ObservableList<Lendet> filteredList = FXCollections.observableArrayList();
        String filterLenda = lendaTextField.getText().toLowerCase();
        LocalDate filterDate = dataDatePicker.getValue();

        for (Lendet lendet : personList) {
            boolean matchesLenda = filterLenda.isEmpty() || lendet.getLenda().toLowerCase().contains(filterLenda);
            boolean matchesDate = filterDate == null || lendet.getData().toLocalDate().isEqual(filterDate);

            if (matchesLenda && matchesDate) {
                filteredList.add(lendet);
            }
        }

        Lendet.setItems(filteredList);
    }
}
