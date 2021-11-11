package com.sportify.sportifyui;

/*!! NON salvate il progetto in una cartella che ha nel suo nome (o nel suo path) uno spazio !!
es.: "C:\User\Nome\Sportify" è ok, "C:\User\Nome\Repository Sportify" non è ok. 
Poi risolverò quessto bug perchè so già cosa devo cambiare ma per ora è così ahaha 
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

    public void showHomeScreen(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Second stage");
        stage.setScene(new Scene(root1));
        root1.getStylesheets().add(Objects.requireNonNull(getClass().getResource("HomeScreenStyle.css")).toExternalForm());
        oldStage.hide(); //chiude la finestra principale prima di aprire quella secondaria
        stage.show();

        //TODO not resizable
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

