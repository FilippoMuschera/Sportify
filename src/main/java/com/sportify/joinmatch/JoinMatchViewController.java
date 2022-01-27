package com.sportify.joinmatch;

import com.sportify.bookmatch.CustomTilePane;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.text.DecimalFormat;


public class JoinMatchViewController {

    @FXML
    private ToggleButton basketToggleButton;
    @FXML
    private ToggleButton footballToggleButton;
    @FXML
    private ToggleButton padelToggleButton;
    @FXML
    private ToggleButton tennisToggleButton;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private Label sportLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Button startButton;
    @FXML
    private TextField resultTextField;
    @FXML
    private TextField hourTextField;
    @FXML
    private ToggleButton distanceToggle;
    @FXML
    private ToggleButton spotsToggle;
    @FXML
    private ScrollPane scrollPaneJoinMatch;

    private ResultSetEntity resultSet;

    private JoinMatchBean beanJoinMatch = new JoinMatchBean();

    private JoinMatchController joinMatchController = new JoinMatchController();

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

    public void setFilterDistance(){
        beanJoinMatch.setDistanceIsImportant(true);
    }

    public void setFilterSpots(){
        beanJoinMatch.setAvailableSpotIsImportant(true);
    }

    public void startJoinMatch(){
        hideControls();
        try{
            beanJoinMatch.setMaxResults(resultTextField.getText());
            beanJoinMatch.setPreferredStartingTime(startTimeLabel.getText());
        }
        catch(IllegalArgumentException e){
            //mettere e.getMessage() su una label
        }
        joinMatchController.findJoinableMatch(beanJoinMatch);
        resultSet = beanJoinMatch.getResultSet();
        scrollPaneJoinMatch.setVisible(true);

        CustomTilePane customTilePane = new CustomTilePane();
        customTilePane.createCustomTilePane();

        for(ResultElement r: resultSet.getElements()){

            Button newButton = new Button("select");
            String sportCenter = r.getNameSC();
            double dist = r.getDistance();
            int courtId = r.getCourtID();
            int start = r.getTimeSlot().getStartTime().getHour();
            int finish = r.getTimeSlot().getEndTime().getHour();
            newButton.setOnAction(event->selectedMatch(r));
            customTilePane.addElement(newButton,sportCenter+" ("+
                    new DecimalFormat("##.##").format(dist)+" kms away), court number: "+courtId+", "
                    +start+":"+finish);

        }

        scrollPaneJoinMatch.setContent(customTilePane.getCustomTP());
    }

    private void hideControls(){
        basketToggleButton.setVisible(false);
        footballToggleButton.setVisible(false);
        padelToggleButton.setVisible(false);
        tennisToggleButton.setVisible(false);
        startTimeLabel.setVisible(false);
        filterLabel.setVisible(false);
        sportLabel.setVisible(false);
        resultLabel.setVisible(false);
        startButton.setVisible(false);
        resultTextField.setVisible(false);
        hourTextField.setVisible(false);
        distanceToggle.setVisible(false);
        spotsToggle.setVisible(false);
    }

    private void selectedMatch(ResultElement selectedMatch){
        //TODO joinMatchController.joinMatch(selectedMatch);
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
