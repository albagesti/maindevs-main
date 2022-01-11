module org.entreculturas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.entreculturas to javafx.fxml;
    exports org.entreculturas;
}