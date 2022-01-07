package com.sportify.bookmatch.statemachine;

public interface BMStateMachineInterface {

    public void initializeState();

    public BMStateInterface getState();

    public void setState(BMStateInterface state);

    public void goPrevState(BMStateInterface state);
}
