package Main;

import Model.OrariKonsultimeve;
import Model.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class


Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/sceneBuilderFiles/login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();

        shfaqTeDhenat();
    }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shfaqTeDhenat() {

        String url = "jdbc:mysql://localhost/knk2024" ;
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Repository repository = new Repository(connection);

            List<OrariKonsultimeve> orari = repository.lexoTëGjithaOraret();


            VBox vbox = new VBox();


            for (OrariKonsultimeve orar : orari) {
                vbox.getChildren().add(new Label("Lenda: " + orar.getLenda()));
                vbox.getChildren().add(new Label("Data: " + orar.getData()));
                vbox.getChildren().add(new Label("Ora: " + orar.getOra()));
                vbox.getChildren().add(new Label("Salla: " + orar.getSalla()));
                vbox.getChildren().add(new Separator());
            }


            Scene scene = new Scene(vbox, 700, 500);


            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Gabim në lidhje me bazën e të dhënave");
            alert.setHeaderText(null);
            alert.setContentText("Ka ndodhur një gabim në lidhjen me bazën e të dhënave.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}