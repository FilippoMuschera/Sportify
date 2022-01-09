package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.bookmatch.BookMatchViewController;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;



public class SportCenterState implements BMStateInterface {

    String userSelectedSport;
    Integer userSortingDistance;
    ObservableList sportCenterList;

    @Override
    public void entry(String userSelectedSport){
        //recupera gli hourslot con le dao
        //disattiva bottoni
        this.userSelectedSport = userSelectedSport;
        //this.userSortingDistance = userSortingDistance;
    }

    @Override
    public ObservableList displayView(){
        BookMatchViewController c = BookMatchViewController.getBookMatchViewControllerInstance();
        ObservableList<String> list = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3", "Item 4");
        c.displaySportCenters(list);
        return sportCenterList;
    }
}
