package com.sportify.sportifyui;

import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private UserEntity user;

    private UserPreferences preferences;


     protected UIController(){ //Singleton GoF Pattern applied to UIController

    }

    public static UIController getUIControllerInstance(){
        if (UIController.singleUIControllerInstance == null){
            UIController.singleUIControllerInstance = new UIController();
        }
        return UIController.singleUIControllerInstance;
    }


    public void showHomeScreen(ActionEvent actionEvent) throws IOException { //Mostra a schermo la home screen
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("HomeScreen.fxml", "HomeScreenStyle.CSS", oldStage);

    }

    public void showSignUp(ActionEvent actionEvent) throws IOException { //Mostra la schermata per il sign up di un nuovo utente
        Stage logInScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        this.loadStage("SignUp.fxml", "SignUpStyle.css", logInScreen);
        setPreviousStageInfo("LogIn.fxml", "LogInStyle.css");

    }
    public void showCreateMatch(ActionEvent actionEvent) throws IOException { //Mostra la schermata create match
         Stage createMatchScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
         this.loadStage("CreateMatch.fxml", "CreateMatchStyle.css", createMatchScreen);

    }

    public void showJoinMatch(ActionEvent actionEvent) throws IOException { //Mostra la schermata join match
        Stage joinMatchScreen = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        this.loadStage("JoinMatch.fxml", "JoinMatchStyle.css", joinMatchScreen);

    }

    public void showSettings(ActionEvent actionEvent) throws IOException { //Mostra la schermata delle impostazioni
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

    public void showAddSportCenter(ActionEvent actionEvent) throws IOException { //Mostra schermata aggiunta sport center
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        this.loadStage("AddSportCenter.fxml", "AddSportCenterStyle.CSS", oldStage);
    }


    // loadStage(...) carica la nuova schermata nello stesso stage
    // se si vuole aprire un pop-up NON va bene!

    protected void loadStage(String stageFXML, String stageCSS, Stage oldStage) throws IOException { //mostra a schermo la schermata passato con i parametri
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stageFXML));
        Parent root1 = fxmlLoader.load();
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stageCSS)).toExternalForm());

        this.fadeAnimation(root1, oldStage.getScene().getRoot());

        oldStage.setScene(new Scene(root1));
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
        Stage stage = new Stage();
        stage.setTitle("Sportify - FAQs");
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("FaqStyle.css")).toExternalForm());

        stage.show();
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
         return this.user;
    }
    //TODO fare in modo che da qualche parte questi 2 metodi sotto siano usati
    //e che creino una UserPreferences class

    /*public void setUserPreferences(UserPreferences preferences) {
        this.preferences = preferences;
    }*/

    /*public UserPreferences getUserPreferences() {
        return this.preferences;
    }*/
}

