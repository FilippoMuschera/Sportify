package com.sportify.bookmatch.statemachine;


public class BMStateMachineImplementation implements BMStateMachineInterface {

    BMStateInterface currentState;
    BMStateInterface prevState;

    //"rendo" singleton BMStateMachineImpl, copiato sempre da te fili :) <3 io l'ho copiato dal De Angelis haha
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
        this.prevState = currentState;
        this.currentState = state;
    }

    @Override
    public void goPrevState(BMStateInterface state){
        this.currentState = prevState;
    }
}
