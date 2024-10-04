module com.mycompany.tp2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.tp2 to javafx.fxml;
    exports com.mycompany.tp2;
}
