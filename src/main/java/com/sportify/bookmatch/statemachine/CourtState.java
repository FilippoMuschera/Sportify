package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterCourts;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;
import java.util.List;


public class CourtState implements BMStateInterface {

    private String[] courtsList;
    private List<SportCourt> courtList;
    private String selectedSport;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();

    private static CourtState singleCourtStateInstance = null;

    public CourtState(){
        singleCourtStateInstance = this;
    }

    public static CourtState getCourtStateInstance(){
        if (CourtState.singleCourtStateInstance == null){
            CourtState.singleCourtStateInstance = new CourtState();
        }
        return CourtState.singleCourtStateInstance;
    }

    @Override
    public void entry(String sportCenterName){

        this.selectedSport = bookMatchController.getSelectedSport();

        SportCenterCourts allCourts = bookMatchController.getBMCourts();

        switch(selectedSport){
            case "Basket":
                courtList = allCourts.getBasketCourts();
                break;
            case "Football":
                courtList = allCourts.getFootballFields();
                break;
            case "Padel":
                courtList = allCourts.getPadelCourts();
                break;
            case "Tennis":
                courtList = allCourts.getTennisCourts();
                break;
            default:
        }

        bookMatchController.setCourtList(courtList);

        int numberOfCourts = courtList.size();
        courtsList = new String[numberOfCourts];
        int i = 0;

        for (SportCourt s: courtList) {
            courtsList[i] = String.valueOf(s.getCourtID());
            i++;
        }
    }

    @Override
    public String[] getList(){
        return courtsList;
    }


}
