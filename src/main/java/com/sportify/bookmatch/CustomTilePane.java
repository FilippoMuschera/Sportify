package com.sportify.bookmatch;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;

public class CustomTilePane {
    TilePane customTP;

    public void addElement(Button selectButton, String contextText){
        selectButton.setPrefSize(105,50);
        Label textLabel = new Label(contextText);
        textLabel.setPrefHeight(60);
        customTP.getChildren().addAll(textLabel,selectButton);
    }

    public void createCustomTilePane(){
        customTP = new TilePane();
        customTP.setPrefColumns(2);
    }

    public TilePane getCustomTP(){
        return customTP;
    }
}
