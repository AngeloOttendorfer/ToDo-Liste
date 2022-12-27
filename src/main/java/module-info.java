module com.example.todoliste {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.todoliste.application;
    opens com.example.todoliste.application to javafx.fxml;
}