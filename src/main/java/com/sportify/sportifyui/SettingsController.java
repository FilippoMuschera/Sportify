package com.sportify.sportifyui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;

import java.io.IOException;

public class SettingsController {

    public void showHome(ActionEvent actionEvent) throws IOException {
        UIController uic = new UIController();
        uic.showHomeScreen(actionEvent);
    }
}
