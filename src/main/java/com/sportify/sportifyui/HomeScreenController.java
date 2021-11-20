package com.sportify.sportifyui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeScreenController {

    @FXML
    private Label addCourtPopupLabel;

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showSettings(actionEvent);
    }

    public void showAddCourt(ActionEvent actionEvent) { //TODO aggiungere schermata "aggiungi centro sportivo"
    }

    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {
        addCourtPopupLabel.setOpacity(0);

    }
}
