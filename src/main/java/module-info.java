module com.bdprojecto.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.bdprojecto.demo3 to javafx.fxml;
    exports com.bdprojecto.demo3;
}