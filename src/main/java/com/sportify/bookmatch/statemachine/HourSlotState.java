package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import javafx.collections.ObservableList;


public class HourSlotState implements BMStateInterface {

    ObservableList hourSlotList;

    @Override
    public ObservableList displayView(){
        return hourSlotList;
    }
    @Override
    public void entry(String court){
        //recupera gli hour slot con le DAO
    }
}
