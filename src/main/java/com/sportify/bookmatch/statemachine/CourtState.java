package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterCourts;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;
import java.util.List;


public class CourtState implements BMStateInterface {

    private List<SportCourt> courtList;
    private String selectedSport;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private SportCenterEntity entitySC;
    private SportCenterCourts allCourts;

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

        selectedSport = bookMatchController.getSelectedSport();
        bookMatchController.setSelectedSportCenter(sportCenterName);

        entitySC = GetSportCenterDAO.getInstance().getSportCenter(sportCenterName,selectedSport);
        bookMatchController.setEntitySC(entitySC);

        allCourts = entitySC.getCourts();
        bookMatchController.setAllCourts(allCourts);

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
    }
}
