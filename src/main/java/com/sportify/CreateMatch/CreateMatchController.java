package com.sportify.CreateMatch;

import com.sportify.sportifyui.UIController;
import javafx.event.ActionEvent;
import com.sportify.user.UserEntity;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CreateMatchController {
    UIController control = UIController.getUIControllerInstance();
    UserEntity user = control.getUser();
    FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("CreateMatch.fxml"));
    //aggiungere i bottoni dello sport in base allo sport

    public void pressedSportCenterButton(){
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("SportCentersView.fxml"));

    };

    public void displaySportCenters(){};

    public void displayCourts(){};

    public void displayHourSlots(){};

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings(actionEvent);
    }

    public void goToHomeScreen(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen(actionEvent);
    }


    public void showJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch(actionEvent);

    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
