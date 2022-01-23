package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.bookmatch.statemachine.CourtState;
import com.sportify.bookmatch.statemachine.HourSlotState;
import com.sportify.sportcenter.AddSportCenterDAO;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import java.util.List;
import java.util.Map;


public class BookMatchController {

    private BMStateMachineImplementation stateMachine;
    private String selectedSport;
    private List<SportCourt> courtList;
    private int selectedCourtID;
    private List<TimeSlot> timeTable;
    private int maxCourtSpot;
    private TimeSlot selectedTimeSlot;
    private String selectedSportCenter;
    private Map<String, Double> nearSportCenters;

    private static BookMatchController singleBookMatchControllerInstance = null;

    protected BookMatchController(){}

    public static BookMatchController getBookMatchControllerInstance(){
        if (BookMatchController.singleBookMatchControllerInstance == null){
            BookMatchController.singleBookMatchControllerInstance = new BookMatchController();
        }
        return BookMatchController.singleBookMatchControllerInstance;
    }


    public Map<String, Double> startStateMachine(String sport){

        stateMachine = BMStateMachineImplementation.getBMStateMachineImplementation();
        stateMachine.initializeState();
        stateMachine.getState().entry(sport);
        return nearSportCenters;
    }

    public List<SportCourt> selectedSportCenter(String sportCenterName){

        stateMachine.setState(new CourtState());
        stateMachine.getState().entry(sportCenterName);
        return courtList;
    }

    public List<TimeSlot> selectedCourt(String courtID){

        this.timeTable = courtList.get(selectedCourtID).getBookingTable();

        stateMachine.setState(new HourSlotState());
        stateMachine.getState().entry(courtID);
        return timeTable;
    }

    public void createJoinMatch(){

        for(TimeSlot t:timeTable){
            if(t == selectedTimeSlot){
                selectedTimeSlot.setAvailableSpots(selectedTimeSlot.getAvailableSpots()-1);
                break;
            }
        }
        updateAvailableSpots();
    }

    public void bookMatch(){

        for(TimeSlot t:timeTable) {
            if (t == selectedTimeSlot) {
                selectedTimeSlot.setAvailableSpots(0);
                break;
            }
        }
        updateAvailableSpots();
    }

    private void updateAvailableSpots(){
        AddSportCenterDAO newAddSportCenterDAO = new AddSportCenterDAO();
        int spots = selectedTimeSlot.getAvailableSpots();
        int startTime = selectedTimeSlot.getStartTime().getHour();
        int finishTime = selectedTimeSlot.getEndTime().getHour();
        newAddSportCenterDAO.updateTimeSlot(spots, selectedCourtID, selectedSport, selectedSportCenter, startTime, finishTime);
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

    public void setSelectedSport(String sport){
        selectedSport = sport;
    }

    public void setSelectedTimeSlot(TimeSlot hourSlot){
        selectedTimeSlot = hourSlot;
    }

    public void setSelectedSportCenter(String selectedSportCenter){
        this.selectedSportCenter = selectedSportCenter;
    }

    public void setNearSportCentersMap(Map<String, Double> nearSportCenters){
        this.nearSportCenters = nearSportCenters;
    }

    public void setMaxCourtSpot(int maxCourtSpot){
        this.maxCourtSpot = maxCourtSpot;
    }

    public int getSelectedCourtID(){
        return selectedCourtID;
    }

    public void setSelectedCourtID(int selectedCourtID){
        this.selectedCourtID = selectedCourtID;
    }

    public void setTimeTable(List<TimeSlot> timeTable){
        this.timeTable = timeTable;
    }
}

