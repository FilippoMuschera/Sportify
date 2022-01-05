package com.sportify.bookmatch.statemachine;

public class BMStateMachineImplementation implements BMStateMachineInterface {

    //"rendo" singleton BMStateMachineImpl, copiato sempre da te fili :) <3
    private static BMStateMachineImplementation singleBMSMInstance = null;

    protected BMStateMachineImplementation(){}

    public static BMStateMachineImplementation getBMStateMachineImplementation(){
        if (BMStateMachineImplementation.singleBMSMInstance == null){
            BMStateMachineImplementation.singleBMSMInstance = new BMStateMachineImplementation();
        }
        return BMStateMachineImplementation.singleBMSMInstance;
    }

    public void initializeState(){}

    public void getState(){}

    public void setState(){}
}
