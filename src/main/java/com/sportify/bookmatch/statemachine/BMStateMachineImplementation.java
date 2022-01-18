package com.sportify.bookmatch.statemachine;


public class BMStateMachineImplementation implements BMStateMachineInterface {

    private BMStateInterface currentState;
    private String selectedSport;

    private static BMStateMachineImplementation singleBMSMInstance = null;

    protected BMStateMachineImplementation(){}

    public static BMStateMachineImplementation getBMStateMachineImplementation(){
        if (BMStateMachineImplementation.singleBMSMInstance == null){
            BMStateMachineImplementation.singleBMSMInstance = new BMStateMachineImplementation();
        }
        return BMStateMachineImplementation.singleBMSMInstance;
    }

    @Override
    public void initializeState(){
        currentState = new SportCenterState();
    }

    @Override
    public BMStateInterface getState(){
        return this.currentState;
    }

    @Override
    public void setState(BMStateInterface state){
        this.currentState = state;
    }

    public void setSelectedSport(String sportName){
        this.selectedSport = sportName;
    }

    public String getSelectedSport(String sportName){
        return this.selectedSport;
    }
}

