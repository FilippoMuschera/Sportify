package com.sportify.sportifyui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
public class AddSportCenterController {

    @FXML
    private Label addCourtPopupLabel;

    @FXML
    private Label outputStateLabel;


    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {addCourtPopupLabel.setOpacity(0); }

    public void goToSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showHomeScreen(actionEvent);
    }

    public void goToJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showJoinMatch(actionEvent);
    }

    public void goToCreateMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showCreateMatch(actionEvent);
    }

    public void addSportCenterToDB() {
        //qui ci sarà l'aggiunta effettiva del centro sportivo al DB

      outputStateLabel.setOpacity(1);

    }
}
