package com.sportify.login;

import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.login.exceptions.IncorrectPasswordException;
import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.utilitiesui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LogInView {
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;


    public void handleLogIn() throws IOException {
        errorLabel.setOpacity(0);
        errorLabel.setTextFill(Color.RED);

        LogInBean bean = new LogInBean();
        bean.setEmail(emailTextField.getText());
        bean.setPassword(passwordField.getText());

        try {
            bean.validateInput();
            LogInController controller = new LogInController();
            controller.logInUser(bean);
            UIController viewController = UIController.getUIControllerInstance();
            viewController.showHomeScreen();
        } catch (EmailNotValidException ee) {
            errorLabel.setText("Invalid email! Try again!");
            errorLabel.setOpacity(1);
        } catch (UserNotFoundException e) {
            errorLabel.setText("User not found. Click on 'Sign Up' to register");
            errorLabel.setOpacity(1);
        } catch (IncorrectPasswordException e) {
            errorLabel.setText("Incorrect password. Try Again!");
            errorLabel.setOpacity(1);
        }

    }

    public void goToSignUp() throws IOException {
        UIController controller = UIController.getUIControllerInstance();
        controller.showSignUp();
    }
}
