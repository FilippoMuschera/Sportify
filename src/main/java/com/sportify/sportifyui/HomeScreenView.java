package com.sportify.sportifyui;


import com.sportify.user.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HomeScreenView {

    @FXML
    public Button addCourt;
    @FXML
    public AnchorPane anchorPaneHomeScreen;
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
    @FXML
    public void initialize(){ //Metodo chiamato dal fxmlloader quando viene caricata questa schermata
        UserEntity user = UIController.getUIControllerInstance().getUser();
        if (Objects.equals(user.getType(), "Player")) {
            /*
            * Se l'utente non è un owner, rimuove il bottone (e la relativa label) "addCourt, poichè la funzionalità
            * non è utilizzabile dal tipo di utente "Player"
             */
            anchorPaneHomeScreen.getChildren().removeAll(addCourt, addCourtPopupLabel);

        }
    }

}