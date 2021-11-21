package com.sportify.sportifyui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class CreateMatchController {

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }

    public void showHomeScreen(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showHomeScreen(actionEvent);
    }

    /*public void showAddCourt(ActionEvent actionEvent) { //TODO aggiungere schermata "aggiungi centro sportivo"
    }

    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {
        addCourtPopupLabel.setOpacity(0);

    }*/
}
