package Controller;

import Model.Suggestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import database.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class NotificationsController {

    public Scene scene;
    public Stage stage;
    public Parent root;

    @FXML
    private TableView<Suggestion> notificationsTable;

    @FXML
    private TableColumn<Suggestion, String> dateColumn;

    @FXML
    private TableColumn<Suggestion, String> timeColumn;

    @FXML
    private TableColumn<Suggestion, String> lendaColumn; // New column
    @FXML
    private Button closeButton;

    public void goToHomeButtonAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/sceneBuilderFiles/profhome.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("suggestedDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("suggestedTime"));
        lendaColumn.setCellValueFactory(new PropertyValueFactory<>("lenda")); // New column setup

        loadNotifications();
    }

    private void loadNotifications() {
        DatabaseUtil dbUtil = new DatabaseUtil();
        Connection conn = dbUtil.getconnection();
        ObservableList<Suggestion> suggestionsList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM suggestions";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String suggestedDate = resultSet.getString("suggested_date");
                String suggestedTime = resultSet.getString("suggested_time");
                String lenda = resultSet.getString("lenda"); // New field

                suggestionsList.add(new Suggestion(suggestedDate, suggestedTime, lenda));
            }

            notificationsTable.setItems(suggestionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
