package com.sportify.utilitiesui;

import com.sportify.signup.SignUpView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class UIController{


    private static String[] previousStageStyles; //tiene traccia del file .fxml (previousStageStyle[0]) e di quello .css (previousStageStyle[1])
                                                 //della schermata precedente, per poterli usare in caso di pulsante back

    private static UIController singleUIControllerInstance = null;


    private Stage stage;

    private UIController(){} //Singleton GoF Pattern applied to UIController


    public static UIController getUIControllerInstance(){
        if (UIController.singleUIControllerInstance == null){
            UIController.singleUIControllerInstance = new UIController();
        }
        return UIController.singleUIControllerInstance;
    }


    public void showHomeScreen() throws IOException { //Mostra a schermo la home screen
        this.loadStage("HomeScreen.fxml", "HomeScreenStyle.CSS");

    }

    public void showSignUp() throws IOException { //Mostra la schermata per il sign up di un nuovo utente
        this.loadStage("SignUp.fxml", "SignUpStyle.css");

    }
    public void showCreateMatch() throws IOException { //Mostra la schermata create match
         this.loadStage("BookMatch.fxml", "BookMatchStyle.css");

    }

    public void showJoinMatch() throws IOException { //Mostra la schermata join match
        this.loadStage("JoinMatch.fxml", "JoinMatchStyle.css");

    }

    public void showSettings() throws IOException { //Mostra la schermata delle impostazioni
        this.loadStage("Settings.fxml", "SettingsStyle.CSS");

    }

    public void showLogIn() throws IOException { //Mostra la schermata di login (se c'è bisogno di tornarci, es.: dal signup
        this.loadStage("LogIn.fxml", "LogInStyle.css");
    }


    public String getPreviousFxml(){
        return  previousStageStyles[0];
    }

    public String getPreviousCss(){
        return  previousStageStyles[1];
    }

    public void showAddSportCenter() throws IOException { //Mostra schermata aggiunta sport center
        this.loadStage("AddSportCenter.fxml", "AddSportCenterStyle.CSS");
    }


    // loadStage(...) carica la nuova schermata nello stesso stage
    // se si vuole aprire un pop-up NON va bene!

    private void loadStage(String stageFXML, String stageCSS) throws IOException { //mostra a schermo la schermata passato con i parametri
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stageFXML));
        Parent root1 = fxmlLoader.load();
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stageCSS)).toExternalForm());

        this.fadeAnimation(root1, this.stage.getScene().getRoot());

        this.stage.setScene(new Scene(root1));
    }

    private void fadeAnimation(Parent screenToFadeIn, Parent screenToFadeOut){//Metodo privato perchè deve essere utilizzato
        this.fadeOut(screenToFadeOut);                                        // solo in loadStage   
        this.fadeIn(screenToFadeIn);
    }

    private void fadeOut(Parent root){
        FadeTransition ft = new FadeTransition(Duration.millis(300), root); //Metodo privato perchè deve essere utilizzato
        ft.setFromValue(1.0);                                               // solo in fadeAnimation
        ft.setToValue(0);
        ft.play();
    }

    private void fadeIn(Parent root){
        FadeTransition ft = new FadeTransition(Duration.millis(300), root); //Metodo privato perchè deve essere utilizzato
        ft.setFromValue(0);                                                 // solo in fadeAnimation
        ft.setToValue(1.0); 
        ft.play();
    }


    public void showFaqs() throws IOException { //Mostra le faq. Non usa loadStage perchè le faq appaiono in una nuova finestra
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("Faq.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Sportify - FAQs");
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setScene(scene);
        newStage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("FaqStyle.css")).toExternalForm());

        newStage.show();
    }

    public void setStage(Stage stage){
         this.stage = stage;
    }


    public void precompileSignUp(String email, String password) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root1 = fxmlLoader.load();
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource("SignUpStyle.css")).toExternalForm());
        SignUpView signUpView = fxmlLoader.getController();
        signUpView.preCompile(email, password);

        this.fadeAnimation(root1, this.stage.getScene().getRoot());

        this.stage.setScene(new Scene(root1));

    }
}

