package com.sportify.bookmatch;

import com.sportify.bookmatch.cells.CourtCell;
import com.sportify.bookmatch.cells.HourSlotCell;
import com.sportify.bookmatch.cells.SportCenterCell;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

//UIController non carica la schermata SportCentersView.fxml non so perche

/*
La chiamata che da problemi è anchorPaneBookMatch.getChildren(), ma non so perchè al momento
 */

public class BookMatchViewController {

    @FXML
     private AnchorPane anchorPaneBookMatch;

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
            basketButton.setOnAction(actionEvent -> bookMatchController.startStateMachine("Basket"));
            basketButton.setStyle("-fx-font-family: \"Playball\"; -fx-font-size: 24");
            anchorPaneBookMatch.getChildren().add(basketButton);

        }

        if (user.getPreferences().getFootball()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button footballButton = new Button("Football");
            footballButton.setPrefSize(150,75);
            footballButton.setLayoutY(300);
            footballButton.setLayoutX(position);
            footballButton.setOnAction(actionEvent -> bookMatchController.startStateMachine("Football"));
            footballButton.setStyle("-fx-font-family: \"Playball\"; -fx-font-size: 24");

            anchorPaneBookMatch.getChildren().add(footballButton);

        }
        if (user.getPreferences().getTennis()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button tennisButton = new Button("Tennis");
            tennisButton.setPrefSize(150,75);
            tennisButton.setLayoutY(300);
            tennisButton.setLayoutX(position);
            tennisButton.setOnAction(actionEvent -> bookMatchController.startStateMachine("Tennis"));
            tennisButton.setStyle("-fx-font-family: \"Playball\"; -fx-font-size: 24");
            anchorPaneBookMatch.getChildren().add(tennisButton);

        }
        if (user.getPreferences().getPadel()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            Button padelButton = new Button("Padel");
            padelButton.setPrefSize(150,75);
            padelButton.setLayoutY(300);
            padelButton.setLayoutX(position);
            padelButton.setOnAction(actionEvent -> bookMatchController.startStateMachine("Padel"));
            padelButton.setStyle("-fx-font-family: \"Playball\"; -fx-font-size: 24");
            anchorPaneBookMatch.getChildren().add(padelButton);

        }
    }

    private static BookMatchViewController singleBookMatchViewControllerInstance = null;

    public BookMatchViewController(){
        BookMatchViewController.setSingletonInstance(this);
    }

    //Siccome settare la variabile singleton nel costruttore (che è anche un metodo non statico) genera code
    //smell, viene invece invocato il metodo setSingletonInstance()

    private static void setSingletonInstance(BookMatchViewController bookMatchViewController) {
        singleBookMatchViewControllerInstance = bookMatchViewController;
    }

    public static BookMatchViewController getBookMatchViewControllerInstance(){
        if (BookMatchViewController.singleBookMatchViewControllerInstance == null){
            BookMatchViewController.singleBookMatchViewControllerInstance = new BookMatchViewController();
        }
        return BookMatchViewController.singleBookMatchViewControllerInstance;
    }


    public void displaySportCenters(ObservableList list) {
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



    //Metodi per bottoni base della UI

    public void showSettings() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings();
    }

    public void goToHomeScreen() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen();
    }


    public void showJoinMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch();

    }

    public void launchFaq() throws IOException, SQLException {

        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();

    }
}
