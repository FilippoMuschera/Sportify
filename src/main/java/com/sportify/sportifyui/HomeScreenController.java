package com.sportify.sportifyui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeScreenController {

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }
}
