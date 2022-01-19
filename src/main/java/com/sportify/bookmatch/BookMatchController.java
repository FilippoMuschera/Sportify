package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.bookmatch.statemachine.CourtState;
import com.sportify.bookmatch.statemachine.HourSlotState;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterCourts;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;

import java.util.List;


public class BookMatchController {

    private BMStateMachineImplementation stateMachine;
    private SportCenterEntity entitySC;
    private String selectedSport;
    private List<SportCourt> courtList;
    private int courtIndex;
    private List<TimeSlot> timeTable;
    private int maxCourtSpot;

    private static BookMatchController singleBookMatchControllerInstance = null;

    protected BookMatchController(){}

    public static BookMatchController getBookMatchControllerInstance(){
        if (BookMatchController.singleBookMatchControllerInstance == null){
            BookMatchController.singleBookMatchControllerInstance = new BookMatchController();
        }
        return BookMatchController.singleBookMatchControllerInstance;
    }


    public String[] startStateMachine(String sport){

        this.selectedSport = sport;

        stateMachine = BMStateMachineImplementation.getBMStateMachineImplementation();
        stateMachine.initializeState();
        stateMachine.getState().entry(sport);

        return stateMachine.getState().getList();
    }

    public String[] selectedSportCenter(String sportCenterName){

        entitySC = GetSportCenterDAO.getInstance().getSportCenter(sportCenterName,selectedSport);

        stateMachine.setState(CourtState.getCourtStateInstance());
        stateMachine.getState().entry(sportCenterName);
        return stateMachine.getState().getList();
    }

    public String[] selectedCourt(String courtID){

        maxCourtSpot = courtList.get(Integer.parseInt(courtID)).getMaxSpots();
        this.courtIndex = Integer.valueOf(courtID);
        this.timeTable = courtList.get(courtIndex).getBookingTable();

        stateMachine.setState(new HourSlotState());
        stateMachine.getState().entry(courtID);
        return stateMachine.getState().getList();
    }

    public void selectedHourSlot(String hourSlot){
        String[] orari = hourSlot.split("-");

        int orarioInizio = Integer.parseInt(orari[0]);
        for(TimeSlot t:timeTable){
            if(t.getStartTime().getHour() == orarioInizio){
                t.setAvailableSpots(maxCourtSpot-1);
            }
            //while(){}
            //forse serve una eccezione

        }
    }

   public List<TimeSlot> getBMTimeSlot(){
        return this.timeTable;
    }

    public SportCenterCourts getBMCourts(){
        return entitySC.getCourts();
    }

    public int getMaxCourtSpot(){
        return maxCourtSpot;
    }

    public void setCourtList(List<SportCourt> list){
        this.courtList = list;
    }

    public List<SportCourt> getCourtList(){
        return this.courtList;
    }

    public String getSelectedSport(){
        return this.selectedSport;
    }

    public void goBack(){

    }
}
