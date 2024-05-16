module org.example.knk_2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    requires com.dlsc.formsfx;

    opens org.example.knk_2024 to javafx.fxml;
    exports org.example.knk_2024;
}




