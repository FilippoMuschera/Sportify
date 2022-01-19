package com.sportify.bookmatch;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterEntity;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.IOException;
import java.util.List;


public class BookMatchViewController {

    @FXML
     private AnchorPane anchorPaneBookMatch;

    @FXML
    private Button basketButton;
    @FXML
    private Button footballButton;
    @FXML
    private Button padelButton;
    @FXML
    private Button tennisButton;
    @FXML
    private Button goBack;

    @FXML
    private ScrollPane scrollPaneBookMatch;

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();

    @FXML
    public void initialize() {
        UIController generalController = UIController.getUIControllerInstance();
        UserEntity user = UserEntity.getInstance();
        int numOfSports = 0;

        if (user.getPreferences().getBasket()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            basketButton.setLayoutX(position);
            basketButton.setVisible(true);
            basketButton.setOnAction(event->startBookMatch(basketButton.getText()));

        }

        if (user.getPreferences().getFootball()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            footballButton.setLayoutX(position);
            footballButton.setVisible(true);
            footballButton.setOnAction(event->startBookMatch(footballButton.getText()));

        }
        if (user.getPreferences().getTennis()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            numOfSports++;
            tennisButton.setLayoutX(position);
            tennisButton.setVisible(true);
            tennisButton.setOnAction(event->startBookMatch(tennisButton.getText()));

        }
        if (user.getPreferences().getPadel()) {

            int position = 200 + numOfSports*150 + numOfSports*100;
            padelButton.setLayoutX(position);
            padelButton.setVisible(true);
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

    public void disableButtons(){
        basketButton.setVisible(false);
        padelButton.setVisible(false);
        footballButton.setVisible(false);
        tennisButton.setVisible(false);
        //goBack.setVisible(false);
    }

    public void enableScrollPane(){
        scrollPaneBookMatch.setVisible(true);
    }

    public void startBookMatch(String selectedSport){
        this.disableButtons();
        this.enableScrollPane();
        String[] sportCenterList = bookMatchController.startStateMachine(selectedSport);
        this.displaySportCenters(sportCenterList);
    }


    @FXML
    public void displaySportCenters(String[] list) {
        //vecchio stile un po ridondante
        //
        /*TilePane tilePaneSC = new TilePane();
        tilePaneSC.setPrefColumns(2);

        for(String element:list) {

            Button selectButton = new Button("select");
            Label textLabel = new Label(element);
            selectButton.setOnAction(event->this.selectedSportCenter(textLabel.getText()));
            tilePaneSC.getChildren().addAll(textLabel,selectButton);
        }
        scrollPaneBookMatch.setContent(tilePaneSC);*/

        CustomTilePane customTilePaneSC = new CustomTilePane();
        customTilePaneSC.createCustomTilePane();

        for(String element:list) {
            Button selectButton = new Button("select");
            selectButton.setOnAction(event->selectedSportCenter(element));
            customTilePaneSC.addElement(selectButton,element);
        }
        scrollPaneBookMatch.setContent(customTilePaneSC.getCustomTilePane());

    }

    public void selectedSportCenter(String sportCenterName){
        //goBack.setVisible(true);
        String[] courtsList = bookMatchController.selectedSportCenter(sportCenterName);
        this.displayCourts(courtsList);
    }


    public void displayCourts(String[] list){

        TilePane tilePaneC = new TilePane();
        tilePaneC.setPrefColumns(2);

        for(String element:list) {

            Button selectButton = new Button("select");
            Label textLabel = new Label(element);
            selectButton.setOnAction(event->selectedCourt(textLabel.getText()));
            tilePaneC.getChildren().addAll(textLabel,selectButton);
        }
        scrollPaneBookMatch.setContent(tilePaneC);

    }

    public void selectedCourt(String ID){
        String[] hourSlotList = bookMatchController.selectedCourt(ID);
        this.displayHourSlots(hourSlotList);
    }

    public void displayHourSlots(String[] list){
        TilePane tilePaneHS = new TilePane();
        tilePaneHS.setPrefColumns(2);

        for(String element:list) {

            Button selectButton = new Button("select");
            Label textLabel = new Label(element);
            selectButton.setOnAction(event->selectedHourSlot(textLabel.getText()));
            tilePaneHS.getChildren().addAll(textLabel,selectButton);
        }
        scrollPaneBookMatch.setContent(tilePaneHS);
    }

    public void selectedHourSlot(String hourSlot){
        bookMatchController.selectedHourSlot(hourSlot);
    }

    public void goBack(){
        bookMatchController.goBack();
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
