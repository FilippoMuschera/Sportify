package com.sportify.sportifyui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class JoinMatchController {
    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings(actionEvent);
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen(actionEvent);
    }

    public void showCreateMatch(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch(actionEvent);
    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
