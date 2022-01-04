package com.sportify.signup;

import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.signup.exceptions.DifferentPasswordException;
import com.sportify.signup.exceptions.UserAlreadyExistsException;
import com.sportify.utilitiesui.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpView {

    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private CheckBox isOwnerCheckBox;
    @FXML
    private Label statusLabel;


    public void goToLogIn(ActionEvent actionEvent) throws IOException {
       UIController controller = UIController.getUIControllerInstance();
       controller.goToPreviousStage(actionEvent);
    }

    public void signUserUp(ActionEvent actionEvent) throws IOException {

            try{
                statusLabel.setOpacity(0);
                SignUpBean bean = new SignUpBean();
                bean.setEmail(emailTextField.getText());
                bean.setFirstPsw(passwordField1.getText());
                bean.setSecondPsw(passwordField2.getText());
                bean.setOwner(isOwnerCheckBox.isSelected());
                bean.executeSignUp();
                UIController controller = UIController.getUIControllerInstance();
                controller.showHomeScreen(actionEvent);
            } catch (DifferentPasswordException e) {
                statusLabel.setText("Passwords are not the same, check them and try again");
            } catch (EmailNotValidException e) {
                statusLabel.setText("The email is not valid");
            } catch (UserAlreadyExistsException e) {
                statusLabel.setText("User already registered. Go to Log In!");
            } finally {
                statusLabel.setOpacity(1);
            }



        }
}
