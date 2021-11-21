package com.sportify.sportifyui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class CreateMatchController {

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }

    public void goToHomeScreen(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showHomeScreen(actionEvent);
    }


    public void showJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showJoinMatch(actionEvent);

    }
}
