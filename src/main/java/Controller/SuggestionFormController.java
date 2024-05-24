package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import database.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class SuggestionFormController {

    @FXML
    private TextField professorEmailField;

    @FXML
    private TextField suggestedDateField;

    @FXML
    private TextField suggestedTimeField;

    @FXML
    private TextField lendaField;
    @FXML
    private Button closeButton;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void sendSuggestion() {
        String professorEmail = professorEmailField.getText();
        String suggestedDate = suggestedDateField.getText();
        String suggestedTime = suggestedTimeField.getText();
        String lenda = lendaField.getText();

        DatabaseUtil dbUtil = new DatabaseUtil();
        Connection conn = dbUtil.getconnection();

        try {
            String query = "INSERT INTO suggestions (professor_email, suggested_date, suggested_time, lenda) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, professorEmail);
            preparedStatement.setString(2, suggestedDate);
            preparedStatement.setString(3, suggestedTime);
            preparedStatement.setString(4, lenda); // New field
            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suggestion Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your suggestion has been sent successfully!");
            alert.showAndWait();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
