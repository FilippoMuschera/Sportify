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
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;


    public void goToLogIn(ActionEvent actionEvent) throws IOException {
       UIController controller = UIController.getUIControllerInstance();
       controller.goToPreviousStage(actionEvent);
    }

    public void signUserUp(ActionEvent actionEvent) throws IOException {

            UIController controller = UIController.getUIControllerInstance();
            controller.showHomeScreen(actionEvent);
        }
}
