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
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.mail;


    opens com.sportify.utilitiesui to javafx.fxml;
    exports com.sportify.utilitiesui;
    exports com.sportify.login;
    exports com.sportify.login.exceptions;
    exports com.sportify.signup.exceptions;
    exports com.sportify.settings.exceptions;
    exports com.sportify.sportcenter.courts;
    exports com.sportify.sportcenter.exceptions;
    exports com.sportify.geolocation;
    exports com.sportify.addsportcenter;
    exports com.sportify.sportcenter;
    exports com.sportify.email;
    opens com.sportify.login to javafx.fxml;
    exports com.sportify.user;
    opens com.sportify.user to javafx.fxml;
    exports com.sportify.bookmatch;
    opens com.sportify.bookmatch to javafx.fxml;
    opens com.sportify.addsportcenter to javafx.fxml;
    exports com.sportify.signup;
    opens com.sportify.signup to javafx.fxml;
    exports com.sportify.settings;
    opens com.sportify.settings to javafx.fxml;
    exports com.sportify.user.observer;
    opens com.sportify.user.observer to javafx.fxml;
    exports com.sportify.joinmatch;
    opens com.sportify.joinmatch to javafx.fxml;
}