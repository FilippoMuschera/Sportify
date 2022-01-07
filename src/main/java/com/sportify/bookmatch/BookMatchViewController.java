package com.sportify.bookmatch;

import com.sportify.bookmatch.cells.CourtCell;
import com.sportify.bookmatch.cells.HourSlotCell;
import com.sportify.utilitiesui.UIController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import com.sportify.user.UserEntity;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import com.sportify.bookmatch.cells.CourtCell;
import com.sportify.bookmatch.cells.SportCenterCell;

//UIController non carica la schermata SportCentersView.fxml non so perche

public class BookMatchViewController {

    @FXML
    static AnchorPane anchorPaneBookMatch;

    @FXML
    public void initialize() {
        UIController generalController = UIController.getUIControllerInstance();
        BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
        UserEntity user = generalController.getUser();
        int numOfSports = 0;

        if (user.getPreferences().getBasket()) {

            numOfSports++;
            Button basketButton = new Button("Basket");
            basketButton.setPrefSize(150,75);
            basketButton.setLayoutY(300);
            basketButton.setLayoutX(200);
            basketButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    bookMatchController.startStateMachine("Basket");
                }
            });
            anchorPaneBookMatch.getChildren().add(basketButton);

        }

        if (user.getPreferences().getFootball()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button footballButton = new Button("Football");
            footballButton.setPrefSize(150,75);
            footballButton.setLayoutY(300);
            footballButton.setLayoutX(position);
            footballButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    bookMatchController.startStateMachine("Football");
                }
            });
            anchorPaneBookMatch.getChildren().add(footballButton);

        }
        if (user.getPreferences().getTennis()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button tennisButton = new Button("Tennis");
            tennisButton.setPrefSize(150,75);
            tennisButton.setLayoutY(300);
            tennisButton.setLayoutX(position);
            tennisButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    bookMatchController.startStateMachine("Tennis");
                }
            });
            anchorPaneBookMatch.getChildren().add(tennisButton);

        }
        if (user.getPreferences().getPadel()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button padelButton = new Button("Padel");
            padelButton.setPrefSize(150,75);
            padelButton.setLayoutY(300);
            padelButton.setLayoutX(position);
            padelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    bookMatchController.startStateMachine("Padel");
                }
            });
            anchorPaneBookMatch.getChildren().add(padelButton);

        }
    }

    public static void displaySportCenters(ObservableList list) {
        ListView<String> sportCentersListView = new ListView<>(list);
        sportCentersListView.setCellFactory(param -> new SportCenterCell());
        anchorPaneBookMatch.getChildren().add(sportCentersListView);
    }

    public void displayCourts(ObservableList list){
        ListView courtList = new ListView(list);
        courtList.setCellFactory(param -> new CourtCell());
        anchorPaneBookMatch.getChildren().add(courtList);
    }

    public void displayHourSlots(ObservableList list){
        ListView hourSlotList = new ListView(list);
        hourSlotList.setCellFactory(param -> new HourSlotCell());
        anchorPaneBookMatch.getChildren().add(hourSlotList);
    }

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
