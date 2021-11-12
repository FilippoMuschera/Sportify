package com.sportify.sportifyui;

/*
!! NON salvate il progetto in una cartella che ha nel suo nome (o nel suo path) uno spazio !!
es.: "C:\User\Nome\Sportify" è ok, "C:\User\Nome\Repository Sportify" non è ok. 
Poi risolverò questo bug perchè so già cosa devo cambiare ma per ora è così ahaha
*/


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UIController extends Application{

    private static Stage previousStage; //tiene traccia della schermata precedente, in modo da sapere a quale si deve tornare
                                        //in caso ci sia un pulsante "back" nella schermata corrente
    private static String[] previousStageStyles; //tiene traccia del file .fxml (previousStageStyle[0]) e di quello .css (previousStageStyle[1])
                                                 //della schermata precedente, per poterli usare in caso di pulsante back

    public void showHomeScreen(ActionEvent actionEvent) throws IOException { //Mostra a schermo la home screen
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("HomeScreen.fxml", "HomeScreenStyle.CSS", oldStage);

    }

    public void showSignUp(ActionEvent actionEvent) throws IOException { //Mostra la schermata per il sign up di un nuovo utente
        Stage logInScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        this.loadStage("SignUp.fxml", "SignUpStyle.css", logInScreen);
        setPreviousStageInfo(logInScreen, "LogIn.fxml", "LogInStyle.css");

    }

    public void goToPreviousStage(ActionEvent actionEvent) throws IOException { //torna alla schermata conservata in previousStage e previousStageStyle
        Stage actualStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage(getPreviousFxml(), getPreviousCss(), actualStage);
    }

    public void setPreviousStageInfo(Stage oldStage, String fxml, String css){
        previousStage = oldStage;
        previousStageStyles = new String[]{fxml, css};
    } //scrive le variabili che conservano lo stage precedente

    public Stage getPreviousStage(){ //POTREBBE NON SERVIRE, DA CONTROLLARE
        return previousStage;
    }

    public String getPreviousFxml(){
        return  previousStageStyles[0];
    }

    public String getPreviousCss(){
        return  previousStageStyles[1];
    }

    public void loadStage(String stageFXML, String StageCSS, Stage stageToHide) throws IOException { //mostra a schermo lo stage passato con i parametri
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stageFXML));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Sportify");
        stage.setScene(new Scene(root1));
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource(StageCSS)).toExternalForm());
        if (stageToHide != null) {
            stageToHide.hide(); //chiude la finestra principale prima di aprire quella secondaria
        }
        stage.setResizable(false);
        stage.show();
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

