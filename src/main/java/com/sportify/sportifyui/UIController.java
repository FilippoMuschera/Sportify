package com.sportify.sportifyui;

/*
!! NON salvate il progetto in una cartella che ha nel suo nome (o nel suo path) uno spazio !!
es.: "C:\User\Nome\Sportify" è ok, "C:\User\Nome\Repository Sportify" non è ok. 
Poi risolverò questo bug perché so già cosa devo cambiare ma per ora è così haha
*/


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class UIController extends Application{


    private static String[] previousStageStyles; //tiene traccia del file .fxml (previousStageStyle[0]) e di quello .css (previousStageStyle[1])
                                                 //della schermata precedente, per poterli usare in caso di pulsante back


    public void showHomeScreen(ActionEvent actionEvent) throws IOException { //Mostra a schermo la home screen
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("HomeScreen.fxml", "HomeScreenStyle.CSS", oldStage);

    }

    public void showSignUp(ActionEvent actionEvent) throws IOException { //Mostra la schermata per il sign up di un nuovo utente
        Stage logInScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        this.loadStage("SignUp.fxml", "SignUpStyle.css", logInScreen);
        setPreviousStageInfo("LogIn.fxml", "LogInStyle.css");

    }
    public void showCreateMatch(ActionEvent actionEvent) throws IOException { //Mostra la schermata  create match
         Stage createMatchScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
         this.loadStage("CreateMatch.fxml", "CreateMatchStyle.css", createMatchScreen);

    }

    public void showJoinMatch(ActionEvent actionEvent) throws IOException { //Mostra la schermata  create match
        Stage joinMatchScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        this.loadStage("JoinMatch.fxml", "JoinMatchStyle.css", joinMatchScreen);

    }

    public void showSettings(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("Settings.fxml", "SettingsStyle.CSS", oldStage);

    }

    public void goToPreviousStage(ActionEvent actionEvent) throws IOException { //torna alla schermata conservata in previousStage e previousStageStyle
        Stage actualStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage(getPreviousFxml(), getPreviousCss(), actualStage);
    }

    public static void setPreviousStageInfo(String fxml, String css){
        previousStageStyles = new String[]{fxml, css};
    } //scrive le variabili che conservano lo stage precedente

    public String getPreviousFxml(){
        return  previousStageStyles[0];
    }

    public String getPreviousCss(){
        return  previousStageStyles[1];
    }

    public void showAddSportCenter(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("AddSportCenter.fxml", "AddSportCenterStyle.CSS", oldStage);
    }


    // loadStage(...) carica la nuova schermata nello stesso stage
    // se si vuole aprire un pop-up NON va bene!

    public void loadStage(String stageFXML, String stageCSS, Stage oldStage) throws IOException { //mostra a schermo la schermata passato con i parametri
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stageFXML));
        Parent root1 = fxmlLoader.load();
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stageCSS)).toExternalForm());

        this.fadeAnimation(root1, oldStage.getScene().getRoot());

        oldStage.setScene(new Scene(root1));
    }

    private void fadeAnimation(Parent screenToFadeIn, Parent screenToFadeOut){
        this.fadeOut(screenToFadeOut);
        this.fadeIn(screenToFadeIn);
    }

    private void fadeOut(Parent root){
        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
    }

    private void fadeIn(Parent root){
        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sportify");
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("LogInStyle.css")).toExternalForm());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}

