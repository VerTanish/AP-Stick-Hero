module com.example.tanishwala {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;


    opens com.example.tanishwala to javafx.fxml;
    exports com.example.tanishwala;
}