package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.bookmatch.statemachine.CourtState;
import com.sportify.bookmatch.statemachine.HourSlotState;
import com.sportify.utilitiesui.UIController;

public class BookMatchController {

    UIController controller = UIController.getUIControllerInstance();
    String userType = controller.getUser().getType();
    String userSelectedSport;
    Integer userSortingDistance = controller.getUser().getPreferences().getSortingDistance();
    BMStateMachineImplementation stateMachine;


    private static BookMatchController singleBookMatchControllerInstance = null;

    protected BookMatchController(){}

    public static BookMatchController getBookMatchControllerInstance(){
        if (BookMatchController.singleBookMatchControllerInstance == null){
            BookMatchController.singleBookMatchControllerInstance = new BookMatchController();
        }
        return BookMatchController.singleBookMatchControllerInstance;
    }


    public void startStateMachine(String sport){
        this.userSelectedSport = sport;
        stateMachine = BMStateMachineImplementation.getBMStateMachineImplementation();
        stateMachine.initializeState();
        //stateMachine.getState().entry(userSelectedSport);
        stateMachine.getState().displayView();
        System.out.println("State machine inizializzata correttamente, I guess");
    }

    public void pressedSportCenter(){
        stateMachine.setState(new CourtState());
        //stateMachine.getState().entry(sportCenter);
        stateMachine.getState().displayView();

    }

    public void pressedCourt(){
        stateMachine.setState(new HourSlotState());
        //stateMachine.getState().entry(court);
        stateMachine.getState().displayView();
    }

    public void pressedHourSlot(){
        //occupati della prenotazione
    }

}
