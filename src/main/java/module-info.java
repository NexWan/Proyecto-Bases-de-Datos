module com.bdprojecto.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires ojdbc10;


    opens com.bdprojecto.demo3 to javafx.fxml;
    exports com.bdprojecto.demo3;
    exports com.bdprojecto.demo3.teacherStuff;
    opens com.bdprojecto.demo3.teacherStuff to javafx.fxml;
    exports com.bdprojecto.demo3.adminStuff;
    opens com.bdprojecto.demo3.adminStuff to javafx.fxml;
    exports com.bdprojecto.demo3.alumnoStuff;
    opens com.bdprojecto.demo3.alumnoStuff to javafx.fxml;
    exports com.bdprojecto.demo3.loginStuff;
    opens com.bdprojecto.demo3.loginStuff to javafx.fxml;
    exports com.bdprojecto.demo3.externalScenes;
    opens com.bdprojecto.demo3.externalScenes to javafx.fxml;
}