package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchViewController;
import javafx.collections.ObservableList;



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
        return sportCenterList;
    }
}
