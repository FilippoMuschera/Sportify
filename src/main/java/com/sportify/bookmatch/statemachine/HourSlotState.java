package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;

public class HourSlotState implements BMStateInterface {

    //array di HourSlot

    private static HourSlotState singleHSSInstance = null;

    protected HourSlotState(){
        //inizializza array HourSlot con le DAO
    }

    public static HourSlotState getHSSInstance(){
        if (HourSlotState.singleHSSInstance == null){
            HourSlotState.singleHSSInstance = new HourSlotState();
        }
        return HourSlotState.singleHSSInstance;
    }

    @Override
    public void displayView(){}
}
