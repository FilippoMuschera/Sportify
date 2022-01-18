package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchViewController;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterCourts;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;

import java.util.ArrayList;
import java.util.List;


public class CourtState implements BMStateInterface {

    private List<Integer> courtArray;
    private String selectedSportCenter;
    private SportCenterEntity currentSC;
    private List<SportCourt> courtList;
    private String selectedSport;

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
    public void displayView(){
        BookMatchViewController c = BookMatchViewController.getBookMatchViewControllerInstance();
        c.displayCourts(courtArray);
    }

    @Override
    public void entry(String sportCenterName){

        this.selectedSportCenter = sportCenterName;
        this.selectedSport = SportCenterState.getSportCenterStateInstance().getUserSelectedSport();

        currentSC = GetSportCenterDAO.getInstance().getSportCenter(sportCenterName,selectedSport);

        SportCenterCourts allCourts;
        allCourts = currentSC.getCourts();
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
        courtArray = new ArrayList<>();

        for (SportCourt s: courtList) {
            courtArray.add(s.getCourtID());
        }


    }


}
