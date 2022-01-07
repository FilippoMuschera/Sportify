package com.sportify.bookmatch.statemachine;

import javafx.collections.ObservableList;


public class CourtState implements BMStateInterface {

    ObservableList courtList;

    @Override
    public ObservableList displayView(){
        //modifica la schermata
        return courtList;
    }

    @Override
    public void entry(String sportCenter){
        //recupera i court con le DAO
    }


}
