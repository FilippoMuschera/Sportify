package com.sportify.bookmatch;

import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.sportcenter.exceptions.SportCenterException;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class BookMatchViewController {

    private static final String TEXT_BUTTON = "select";
    @FXML
    private Label meanLabel;
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
    @FXML
    private Label successLabel;
    @FXML
    private Label errorLabel;

    private boolean basketButtonIsActive = false;
    private boolean padelButtonIsActive = false;
    private boolean tennisButtonIsActive = false;
    private boolean footballButtonIsActive = false;

    @FXML
    private ScrollPane scrollPaneBookMatch;

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private CustomTilePane customTilePane = new CustomTilePane();

    @FXML
    public void initialize(){

        successLabel.setOpacity(0);
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

    private void hideButtons(){
        basketButton.setVisible(false);
        padelButton.setVisible(false);
        footballButton.setVisible(false);
        tennisButton.setVisible(false);
        errorLabel.setVisible(false);
    }

    private void enableButtons(){
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

    private void enableScrollPane(){
        scrollPaneBookMatch.setVisible(true);
    }

    private void startBookMatch(String selectedSport){
        this.hideButtons();
        Map<String, Double> sportCenterList = null;
        try {
            sportCenterList = bookMatchController.startStateMachine(selectedSport);
            enableScrollPane();
            displaySportCenters(sportCenterList);
        }
        catch(SportCenterException e){
            displayError();
        }
    }

    private void displayError(){
        errorLabel.setVisible(true);
        errorLabel.setText("""
                    There are no Sport Centers in your area.
                    Please change your address in Settings.""");
    }

    @FXML
    private void displaySportCenters(Map<String, Double> nearSportCenters) {

        customTilePane.createCustomTilePane();
        meanLabel.setVisible(true);
        meanLabel.setText("These are the nearest Sport Center, choose one.");

        nearSportCenters.forEach((key, value) -> {
            Button selectButton = new Button(TEXT_BUTTON);
            selectButton.setOnAction(event -> selectedSportCenter(key));
            customTilePane.addElement(selectButton, "Sport Center " + key + "   (" + new DecimalFormat("##.##").format(nearSportCenters.get(key))+ " kms away)");
        });
        scrollPaneBookMatch.setContent(customTilePane.getCustomTP());

    }

    private void selectedSportCenter(String sportCenterName){
        List<SportCourt> courtsList = bookMatchController.selectedSportCenter(sportCenterName);
        this.displayCourts(courtsList);
    }


    private void displayCourts(List<SportCourt> courtList){

        customTilePane.createCustomTilePane();
        meanLabel.setText("Select which court you want.");

        for(SportCourt element:courtList) {
            Button selectButton = new Button(TEXT_BUTTON);
            selectButton.setOnAction(event->selectedCourt(String.valueOf(element.getCourtID())));
            customTilePane.addElement(selectButton,"Court number: "+element.getCourtID());
        }
        scrollPaneBookMatch.setContent(customTilePane.getCustomTP());

    }

    private void selectedCourt(String id){
        List<TimeSlot> timeTable = bookMatchController.selectedCourt(id);
        this.displayHourSlots(timeTable);
    }

    private void displayHourSlots(List<TimeSlot> timeTable){
        customTilePane.createCustomTilePane();
        meanLabel.setText("Select which time slot you prefer.");

        for(TimeSlot element:timeTable) {
            Button selectButton = new Button(TEXT_BUTTON);
            selectButton.setOnAction(event-> selectedHourSlot(element));
            int start = element.getStartTime().getHour();
            int finish = element.getEndTime().getHour();
            int spots = element.getAvailableSpots();
            customTilePane.addElement(selectButton,"Start time: "+start+ "   Finish time: "+finish+", available spots: "+spots);
        }
        scrollPaneBookMatch.setContent(customTilePane.getCustomTP());
    }

    private void selectedHourSlot (TimeSlot hourSlot){
        bookMatchController.setSelectedTimeSlot(hourSlot);
        meanLabel.setVisible(false);

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

    private void restartBookMatch(){

        hidePopUpControls();
        enableButtons();
        displaySuccessLabel();
    }

    private void displaySuccessLabel(){

        Timeline blinker = createBlinker(successLabel);
        FadeTransition fader = createFader(successLabel);
        SequentialTransition blinkThenFade = new SequentialTransition(
                successLabel,
                blinker,
                fader
        );
        fader.setOnFinished(event -> successLabel.setVisible(false));
        blinkThenFade.play();
    }

    private Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(node.visibleProperty(), true, Interpolator.DISCRETE)));
        blink.setCycleCount(1);

        return blink;
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

    private void showPopUpControls(){
        createJoinMatchButton.setVisible(true);
        bookMatchButton.setVisible(true);
        popUpLabel.setVisible(true);
        scrollPaneBookMatch.setVisible(false);
        popUpAnchorPane.setVisible(true);
    }

    private void hidePopUpControls(){
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

    public void launchFaq() throws IOException{
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
