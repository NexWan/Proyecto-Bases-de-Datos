module com.bdprojecto.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires ojdbc10;


    opens com.bdprojecto.demo3 to javafx.fxml;
    exports com.bdprojecto.demo3;
}