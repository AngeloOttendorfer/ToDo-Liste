module com.example.todoliste {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.todoliste to javafx.fxml;
    exports com.example.todoliste;
}