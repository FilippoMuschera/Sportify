package com.sportify.sportifyui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField1, passwordField2;


    public void goToLogIn(ActionEvent actionEvent) throws IOException {
       UIController controller = new UIController();
       controller.goToPreviousStage(actionEvent);
    }

    public void signUserUp(ActionEvent actionEvent) throws IOException {

        LogIn signUp = new LogIn();
        if(signUp.registerNewUser(emailTextField.getText(), passwordField1.getText(), passwordField2.getText())){
            System.out.println("User registered successfully");
            UIController controller = new UIController();
            controller.showHomeScreen(actionEvent);
        }

     }
}
