package com.sportify.joinmatch;

import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.util.List;




public class JoinMatchViewController {

    @FXML
    private ToggleButton basketToggleButton;
    @FXML
    private ToggleButton footballToggleButton;
    @FXML
    private ToggleButton padelToggleButton;
    @FXML
    private ToggleButton tennisToggleButton;

    private ResultSetEntity resultSet;

    private JoinMatchBean beanJoinMatch = new JoinMatchBean();

    public void initialize(){

        UserEntity user = UserEntity.getInstance();

        int numOfSports = 0;

        if (user.getPreferences().getBasket()) {

            int position = 550 + numOfSports*100 + numOfSports*75;
            numOfSports++;
            basketToggleButton.setLayoutX(position);
            basketToggleButton.setVisible(true);
            basketToggleButton.setOnAction(event->beanJoinMatch.setSelectedSport("Basket"));

        }

        if (user.getPreferences().getFootball()) {

            int position = 550 + numOfSports*100 + numOfSports*75;
            numOfSports++;
            footballToggleButton.setLayoutX(position);
            footballToggleButton.setVisible(true);
            footballToggleButton.setOnAction(event->beanJoinMatch.setSelectedSport("Football"));

        }
        if (user.getPreferences().getTennis()) {

            int position = 550 + numOfSports*100 + numOfSports*75;
            numOfSports++;
            tennisToggleButton.setLayoutX(position);
            tennisToggleButton.setVisible(true);
            tennisToggleButton.setOnAction(event->beanJoinMatch.setSelectedSport("Tennis"));

        }
        if (user.getPreferences().getPadel()) {

            int position = 550 + numOfSports*100 + numOfSports*75;
            padelToggleButton.setLayoutX(position);
            padelToggleButton.setVisible(true);
            padelToggleButton.setOnAction(event->beanJoinMatch.setSelectedSport("Padel"));
        }
    }

    public void showSettings() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings();
    }

    public void goToHome() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen();
    }

    public void showCreateMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch();
    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }


}
