package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.bookmatch.statemachine.CourtState;
import com.sportify.bookmatch.statemachine.HourSlotState;
import com.sportify.utilitiesui.UIController;

public class BookMatchController {

    private BMStateMachineImplementation stateMachine;


    private static BookMatchController singleBookMatchControllerInstance = null;

    protected BookMatchController(){}

    public static BookMatchController getBookMatchControllerInstance(){
        if (BookMatchController.singleBookMatchControllerInstance == null){
            BookMatchController.singleBookMatchControllerInstance = new BookMatchController();
        }
        return BookMatchController.singleBookMatchControllerInstance;
    }


    public void startStateMachine(String sport){
        stateMachine = BMStateMachineImplementation.getBMStateMachineImplementation();
        stateMachine.initializeState();
        stateMachine.getState().entry(sport);
        stateMachine.getState().displayView();
    }

    public void pressedSportCenter(String sportCenterName){
        stateMachine.setState(new CourtState());
        stateMachine.getState().entry(sportCenterName);
        stateMachine.getState().displayView();
    }

    public void pressedCourt(String courtID){
        stateMachine.setState(new HourSlotState());
        stateMachine.getState().entry(courtID);
        stateMachine.getState().displayView();
    }

    public void pressedHourSlot(){
        //occupati della prenotazione
    }

    public void goBack(){

    }

}
