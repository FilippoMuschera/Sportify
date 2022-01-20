package com.sportify.bookmatch;

import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.sql.SQLException;
import java.io.IOException;


public class BookMatchViewController {

    private final String textButtons = "select";

    @FXML
    private AnchorPane anchorPaneBookMatch;
    @FXML
    private Label popUpLabel;
    @FXML
    private AnchorPane popUpAnchorPane;
    @FXML
    private Button createJoinMatchButton;
    @FXML
    private Button bookMatchButton;
    @FXML
    private Button basketButton;
    @FXML
    private Button footballButton;
    @FXML
    private Button padelButton;
    @FXML
    private Button tennisButton;

    private boolean basketButtonIsActive = false;
    private boolean padelButtonIsActive = false;
    private boolean tennisButtonIsActive = false;
    private boolean footballButtonIsActive = false;

    @FXML
    private ScrollPane scrollPaneBookMatch;

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private CustomTilePane customTilePane = new CustomTilePane();

    @FXML
    public void initialize() {
        UserEntity user = UserEntity.getInstance();

        int numOfSports = 0;

        if (user.getPreferences().getBasket()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            basketButton.setLayoutX(position);
            basketButton.setVisible(true);
            basketButtonIsActive = true;
            basketButton.setOnAction(event->startBookMatch(basketButton.getText()));

        }

        if (user.getPreferences().getFootball()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            footballButton.setLayoutX(position);
            footballButton.setVisible(true);
            footballButtonIsActive = true;
            footballButton.setOnAction(event->startBookMatch(footballButton.getText()));

        }
        if (user.getPreferences().getTennis()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            tennisButton.setLayoutX(position);
            tennisButton.setVisible(true);
            tennisButtonIsActive = true;
            tennisButton.setOnAction(event->startBookMatch(tennisButton.getText()));

        }
        if (user.getPreferences().getPadel()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            padelButton.setLayoutX(position);
            padelButton.setVisible(true);
            padelButtonIsActive = true;
            padelButton.setOnAction(event->startBookMatch(padelButton.getText()));
        }
    }


    private static BookMatchViewController singleBookMatchViewControllerInstance = null;

    public BookMatchViewController(){
        singleBookMatchViewControllerInstance = this;
    }

    public static BookMatchViewController getBookMatchViewControllerInstance(){
        if (BookMatchViewController.singleBookMatchViewControllerInstance == null){
            BookMatchViewController.singleBookMatchViewControllerInstance = new BookMatchViewController();
        }
        return BookMatchViewController.singleBookMatchViewControllerInstance;
    }

    public void hideButtons(){
        basketButton.setVisible(false);
        padelButton.setVisible(false);
        footballButton.setVisible(false);
        tennisButton.setVisible(false);
    }

    public void enableButtons(){
        if(basketButtonIsActive) {
            basketButton.setVisible(true);
        }
        if(padelButtonIsActive){
            padelButton.setVisible(true);
        }
        if(footballButtonIsActive){
            footballButton.setVisible(true);
        }
        if(tennisButtonIsActive) {
            tennisButton.setVisible(true);
        }
    }

    public void enableScrollPane(){
        scrollPaneBookMatch.setVisible(true);
    }

    public void startBookMatch(String selectedSport){
        this.hideButtons();
        this.enableScrollPane();
        String[] sportCenterList = bookMatchController.startStateMachine(selectedSport);
        this.displaySportCenters(sportCenterList);
    }


    @FXML
    public void displaySportCenters(String[] list) {

        customTilePane.createCustomTilePane();

        for(String element:list) {
            Button selectButton = new Button(textButtons);
            selectButton.setOnAction(event->selectedSportCenter(element));
            customTilePane.addElement(selectButton,element);
        }
        scrollPaneBookMatch.setContent(customTilePane.getCustomTilePane());

    }

    public void selectedSportCenter(String sportCenterName){
        //goBack.setVisible(true);
        String[] courtsList = bookMatchController.selectedSportCenter(sportCenterName);
        this.displayCourts(courtsList);
    }


    public void displayCourts(String[] list){

        customTilePane.createCustomTilePane();

        for(String element:list) {
            Button selectButton = new Button(textButtons);
            selectButton.setOnAction(event->selectedCourt(element));
            customTilePane.addElement(selectButton,element);
        }
        scrollPaneBookMatch.setContent(customTilePane.getCustomTilePane());

    }

    public void selectedCourt(String ID){
        String[] hourSlotList = bookMatchController.selectedCourt(ID);
        this.displayHourSlots(hourSlotList);
    }

    public void displayHourSlots(String[] list){
        customTilePane.createCustomTilePane();

        for(String element:list) {
            Button selectButton = new Button(textButtons);
            selectButton.setOnAction(event-> selectedHourSlot(element));
            customTilePane.addElement(selectButton,element);
        }
        scrollPaneBookMatch.setContent(customTilePane.getCustomTilePane());
    }

    public void selectedHourSlot (String hourSlot){
        bookMatchController.setHourSlot(hourSlot);

        popUpLabel.setText("""
                If you want to book a match, click Book Match.
                
                If you want to create a joinable match, click
                Create Joinable Match.
                
                """);

        showPopUpControls();
    }

    public void createBookMatch(){
        bookMatchController.bookMatch();
        restartBookMatch();
    }

    public void createJoinMatch(){
        bookMatchController.createJoinMatch();
        restartBookMatch();
    }

    public void restartBookMatch(){

        hidePopUpControls();
        enableButtons();
    }

    public void showPopUpControls(){
        createJoinMatchButton.setVisible(true);
        bookMatchButton.setVisible(true);
        popUpLabel.setVisible(true);
        scrollPaneBookMatch.setVisible(false);
        popUpAnchorPane.setVisible(true);
    }

    public void hidePopUpControls(){
        createJoinMatchButton.setVisible(false);
        bookMatchButton.setVisible(false);
        popUpLabel.setVisible(false);
        popUpAnchorPane.setVisible(false);
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
