package com.sportify.sportifyui;

import javafx.event.ActionEvent;


import java.io.IOException;

public class SettingsController {

    public void showHome(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showHomeScreen(actionEvent);
    }

    public void goToJoinScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showJoinMatch(actionEvent);
    }

    public void goToCreateScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showCreateMatch(actionEvent);
    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
