module com.example.tanishwala {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tanishwala to javafx.fxml;
    exports com.example.tanishwala;
}