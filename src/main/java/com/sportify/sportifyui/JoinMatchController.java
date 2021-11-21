package com.sportify.sportifyui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class JoinMatchController {
    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showHomeScreen(actionEvent);
    }

    public void showCreateMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showCreateMatch(actionEvent);
    }
}
