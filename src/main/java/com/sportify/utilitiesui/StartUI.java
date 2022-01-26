package com.sportify.utilitiesui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class StartUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(new FileInputStream("src/main/resources/com/sportify/utilitiesui/fonts/Playball-Regular.ttf"), 10);
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sportify");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("icons/mainIcon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("LogInStyle.css")).toExternalForm());
        UIController viewController = UIController.getUIControllerInstance();
        viewController.setStage(stage);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
