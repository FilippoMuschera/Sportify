module com.sportify.sportifyui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jasypt;
    requires java.sql;


    opens com.sportify.sportifyui to javafx.fxml;
    exports com.sportify.sportifyui;
    exports com.sportify.login;
    exports com.sportify.login.exceptions;
    exports com.sportify.signup.exceptions;
    opens com.sportify.login to javafx.fxml;
    exports com.sportify.user;
    opens com.sportify.user to javafx.fxml;
    exports com.sportify.CreateMatch;
    opens com.sportify.CreateMatch to javafx.fxml;
}