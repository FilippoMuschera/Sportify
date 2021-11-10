package com.sportify.sportifyui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {
    @FXML private Button logInButton;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;


    public void handleLogIn(ActionEvent actionEvent) throws IOException {
        LogIn log = new LogIn();
        if (log.authLogIn(emailTextField.getText(), passwordField.getText())){
            System.out.println("Succesfull login!");
            UIController controller = new UIController();
            controller.showHomeScreen(actionEvent);
        }
        else{
            //TODO raise error
        }
    }
}
