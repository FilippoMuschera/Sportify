package com.sportify.sportifyui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeScreenController {

    @FXML
    private Label addCourtPopupLabel;

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings(actionEvent);
    }

    public void showCreateMatch(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch(actionEvent);
    }

    public void showJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch(actionEvent);
    }

    public void showAddCourt(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showAddSportCenter(actionEvent);
    }

    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {addCourtPopupLabel.setOpacity(0);

    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
