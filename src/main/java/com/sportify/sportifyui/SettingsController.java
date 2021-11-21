package com.sportify.sportifyui;

import javafx.event.ActionEvent;


import java.io.IOException;

public class SettingsController {

    public void showHome(ActionEvent actionEvent) throws IOException {
        UIController uic = new UIController();
        uic.showHomeScreen(actionEvent);
    }

    public void goToJoinScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = new UIController();
        uic.showJoinMatch(actionEvent);
    }

    public void goToCreateScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = new UIController();
        uic.showCreateMatch(actionEvent);
    }
}
