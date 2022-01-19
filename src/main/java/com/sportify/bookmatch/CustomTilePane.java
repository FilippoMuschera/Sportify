package com.sportify.bookmatch;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class CustomTilePane {
    TilePane customTilePane;

    public void addElement(Button selectButton, String contextText){
        selectButton.setPrefSize(105,50);
        Label textLabel = new Label(contextText);
        textLabel.setPrefHeight(60);
        customTilePane.getChildren().addAll(textLabel,selectButton);
    }

    public void createCustomTilePane(){
        customTilePane = new TilePane();
        customTilePane.setPrefColumns(2);
    }

    public TilePane getCustomTilePane(){
        return customTilePane;
    }
}
