package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import javafx.collections.ObservableList;


public class HourSlotState implements BMStateInterface {

    String[] hourSlotList;

    @Override
    public void displayView(){

        //return hourSlotList;
    }
    @Override
    public void entry(String court){
        //recupera gli hour slot con le DAO
    }
}
