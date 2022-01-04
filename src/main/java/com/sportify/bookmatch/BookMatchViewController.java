package com.sportify.bookmatch;

import com.sportify.user.UserPreferences;
import com.sportify.utilitiesui.UIController;
import javafx.event.ActionEvent;
import com.sportify.user.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class BookMatchViewController {

    @FXML
    public AnchorPane anchorPaneBookMatch;

    @FXML
    public void initialize() {
        UIController control = UIController.getUIControllerInstance();
        UserEntity user = control.getUser();
        Integer numOfSports = 0;
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("BookMatch.fxml"));

        //if (user.getPreferences().getBasket()) {
        if (true) {
            numOfSports++;
            Button basketButton = new Button("Basket");
            basketButton.setPrefSize(150,75);
            basketButton.setLayoutY(300);
            basketButton.setLayoutX(200);
            anchorPaneBookMatch.getChildren().add(basketButton);

        }

        //if (user.getPreferences().getFootball()) {
        if (true) {

            Integer position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button footballButton = new Button("Football");
            footballButton.setPrefSize(150,75);
            footballButton.setLayoutY(300);
            footballButton.setLayoutX(position);
            anchorPaneBookMatch.getChildren().add(footballButton);

        }
        //if (user.getPreferences().getTennis()) {
        if (true) {

            Integer position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button tennisButton = new Button("Tennis");
            tennisButton.setPrefSize(150,75);
            tennisButton.setLayoutY(300);
            tennisButton.setLayoutX(position);
            anchorPaneBookMatch.getChildren().add(tennisButton);

        }
        //if (user.getPreferences().getPadel()) {
        if (true) {

            Integer position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button padelButton = new Button("Padel");
            padelButton.setPrefSize(150,75);
            padelButton.setLayoutY(300);
            padelButton.setLayoutX(position);
            anchorPaneBookMatch.getChildren().add(padelButton);

        }
    }

    public void pressedSportCenterButton(){
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("SportCentersView.fxml"));

    };

    public void displaySportCenters(){};

    public void displayCourts(){};

    public void displayHourSlots(){};

    public void showSettings(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings(actionEvent);
    }

    public void goToHomeScreen(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen(actionEvent);
    }


    public void showJoinMatch(ActionEvent actionEvent) throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch(actionEvent);

    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
