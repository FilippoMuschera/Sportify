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

    public void showCreateMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showCreateMatch(actionEvent);
    }

    public void showJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showJoinMatch(actionEvent);
    }

    public void showAddCourt(ActionEvent actionEvent) throws IOException {
        UIController c = new UIController();
        c.showAddSportCenter(actionEvent);
    }

    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {addCourtPopupLabel.setOpacity(0);

    }
}
