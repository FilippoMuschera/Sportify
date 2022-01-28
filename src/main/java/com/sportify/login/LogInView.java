package com.sportify.login;

import com.sportify.login.exceptions.LoginFailedException;
import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.utilitiesui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LogInView {
    @FXML public Pane popUpPane;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;


    public void handleLogIn() throws IOException {
        errorLabel.setOpacity(0);
        errorLabel.setTextFill(Color.RED);

        LogInBean bean = new LogInBean();


        try {

            bean.setEmail(emailTextField.getText());
            bean.setPassword(passwordField.getText());
            LogInController controller = new LogInController();
            controller.logInUser(bean);
            UIController viewController = UIController.getUIControllerInstance();
            viewController.showHomeScreen();

        } catch (IllegalArgumentException | LoginFailedException exception) {
            errorLabel.setText(exception.getMessage());
            errorLabel.setOpacity(1);
        } catch (UserNotFoundException e) {
            /* In questo caso si sceglie di gestire l'eccezione. Se l'utente inserisce una email che non è nel DB
            * allora significa che non è registrato (oppure che ha sbagliato email). Per questo si procede prima a chiedere
            * conferma della scelta da effettuare (registrarsi o riprovare il login), poi, se l'utente sceglie di registrarsi
            * Lo si porta nella schermata di signup, e si compila quest'ultima con l'email e la password che ha già inserito
            * quando si è provato a loggare, in modo che per registrarsi dovrà solamente confermare la password e scegliere se
            * spuntare la casella "sport center owner"
             */
            this.popUpPane.setOpacity(1);
            this.popUpPane.setMouseTransparent(false);



        }



    }

    public void goToSignUp() throws IOException {
        UIController controller = UIController.getUIControllerInstance();
        controller.showSignUp();
    }

    @FXML
    public void initialize(){
        this.closePopUp();
    }

    public void precompiledSignUp() throws IOException {
        UIController viewController = UIController.getUIControllerInstance();
        viewController.precompileSignUp(emailTextField.getText(), passwordField.getText());
    }

    public void closePopUp() {
        this.popUpPane.setOpacity(0);
        this.popUpPane.setMouseTransparent(true);
    }
}
