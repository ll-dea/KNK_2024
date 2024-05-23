package Controller;//package Controller;

import Model.Suggestion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import database.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotificationsController {

    @FXML
    private TableView<Suggestion> notificationsTable;

    @FXML
    private TableColumn<Suggestion, String> dateColumn;

    @FXML
    private TableColumn<Suggestion, String> timeColumn;



    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("suggestedDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("suggestedTime"));

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

                suggestionsList.add(new Suggestion(suggestedDate, suggestedTime));
            }

            notificationsTable.setItems(suggestionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

