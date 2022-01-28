package com.sportify.bookmatch;

import com.sportify.bookmatch.statemachine.BMStateMachineImplementation;
import com.sportify.bookmatch.statemachine.CourtState;
import com.sportify.bookmatch.statemachine.HourSlotState;
import com.sportify.email.EmailThread;
import com.sportify.sportcenter.AddSportCenterDAO;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterInfo;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.sportcenter.exceptions.SportCenterException;
import com.sportify.user.UserEntity;
import java.util.List;
import java.util.Map;


public class BookMatchController {

    private BMStateMachineImplementation stateMachine;
    private String selectedSport;
    private List<SportCourt> courtList;
    private int selectedCourtID;
    private List<TimeSlot> timeTable;
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


    public Map<String, Double> startStateMachine(String sport) throws SportCenterException{

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
        this.sendEmail();
    }

    public void bookMatch(){

        for(TimeSlot t:timeTable) {
            if (t == selectedTimeSlot) {
                selectedTimeSlot.setAvailableSpots(0);
                break;
            }
        }
        updateAvailableSpots();
        this.sendEmail();
    }

    private void updateAvailableSpots(){
        AddSportCenterDAO newAddSportCenterDAO = new AddSportCenterDAO();
        int spots = selectedTimeSlot.getAvailableSpots();
        int startTime = selectedTimeSlot.getStartTime().getHour();
        int finishTime = selectedTimeSlot.getEndTime().getHour();
        newAddSportCenterDAO.updateTimeSlot(spots, selectedCourtID, selectedSport, selectedSportCenter, startTime, finishTime);
    }

    public void sendEmail(){

        UserEntity user = UserEntity.getInstance();
        SportCenterInfo sportCenterInfo = GetSportCenterDAO.getInstance().getSportCenter(selectedSportCenter,selectedSport).getInfo();
        int startTime = selectedTimeSlot.getStartTime().getHour();
        int finishTime = selectedTimeSlot.getEndTime().getHour();
        SportCenterInfo infoSportCenter = GetSportCenterDAO.getInstance().getSportCenter(selectedSportCenter,selectedSport).getInfo();
        if(user.getPreferences().isNotifications()) {
            EmailThread playerEmailThread = new EmailThread(selectedSport, selectedCourtID, startTime, finishTime, infoSportCenter.getSportCenterAddress());
            playerEmailThread.setPlayer(true);
            playerEmailThread.start();
        }
        if(sportCenterInfo.isNotifications()) {
            EmailThread ownerEmailThread = new EmailThread(infoSportCenter.getOwnerEmail(), selectedSport, selectedCourtID, startTime, finishTime);
            ownerEmailThread.setOwner(true);
            ownerEmailThread.start();
        }

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

