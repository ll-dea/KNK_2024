module org.example.knk_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    requires com.dlsc.formsfx;
    requires mysql.connector.j;

    opens Controller to javafx.fxml;
    exports Controller;
    exports Main;
    opens Main to javafx.fxml;
    exports Model;
    opens Model to javafx.fxml;
}




