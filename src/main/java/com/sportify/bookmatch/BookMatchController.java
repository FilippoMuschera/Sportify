package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.utilitiesui.UIController;

public class BookMatchController {

    UIController controller = UIController.getUIControllerInstance();
    String userType = controller.getUser().getType();
    String userSelectedSport;
    Integer userSortingDistance = controller.getUser().getPreferences().getSortingDistance();

    //"rendo" singleton BookMatchController, copiato da te fili :)
    private static BookMatchController singleBookMatchControllerInstance = null;

    protected BookMatchController(){}

    public static BookMatchController getBookMatchControllerInstance(){
        if (BookMatchController.singleBookMatchControllerInstance == null){
            BookMatchController.singleBookMatchControllerInstance = new BookMatchController();
        }
        return BookMatchController.singleBookMatchControllerInstance;
    }

    //non so se servira mai <----
    protected BookMatchController(String sport){
        this.userSelectedSport = sport;
    }

    private void startStateMachine(){
        BMStateMachineImplementation stateMachine = BMStateMachineImplementation.getBMStateMachineImplementation();
    }

    public void displaySportCenters(String sport){
        this.userSelectedSport = sport;
    }

    public void displayCourts(){}

    public void displayHourSlots(){}

}
