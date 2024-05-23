package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import database.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuggestionFormController {

    @FXML
    private TextField professorEmailField;

    @FXML
    private TextField suggestedDateField;

    @FXML
    private TextField suggestedTimeField;

    @FXML
    private TextField lendaField; // New field

    public void sendSuggestion() {
        String professorEmail = professorEmailField.getText();
        String suggestedDate = suggestedDateField.getText();
        String suggestedTime = suggestedTimeField.getText();
        String lenda = lendaField.getText(); // New field

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
