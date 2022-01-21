package com.sportify.utilitiesui;


import com.sportify.user.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HomeScreenView {

    @FXML
    private Button addCourt;
    @FXML
    private AnchorPane anchorPaneHomeScreen;
    @FXML
    private Label addCourtPopupLabel;


    public void showSettings() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings();
    }

    public void showCreateMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch();
    }

    public void showJoinMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch();
    }

    public void showAddCourt() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showAddSportCenter();
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
        UserEntity user = UserEntity.getInstance();
        if (Objects.equals(user.getType(), "Player")) {
            /*
            * Se l'utente non è un owner, rimuove il bottone (e la relativa label) "addCourt, poichè la funzionalità
            * non è utilizzabile dal tipo di utente "Player"
             */
            anchorPaneHomeScreen.getChildren().removeAll(addCourt, addCourtPopupLabel);

        }
    }

}
