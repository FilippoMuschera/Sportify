package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchViewController;
import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private String userSelectedSport;
    private String[] sportCenterList;
    private Map<String, Double> nearSportCenters;

    private static SportCenterState singleSportCenterStateInstance = null;

    protected SportCenterState(){
        singleSportCenterStateInstance = this;
    }

    public static SportCenterState getSportCenterStateInstance(){
        if (SportCenterState.singleSportCenterStateInstance == null){
            SportCenterState.singleSportCenterStateInstance = new SportCenterState();
        }
        return SportCenterState.singleSportCenterStateInstance;
    }

    @Override
    public void entry(String userSelectedSport){

        this.userSelectedSport = userSelectedSport;

        GetSportCenterDAO getSCDAO;
        getSCDAO = GetSportCenterDAO.getInstance();
        nearSportCenters = getSCDAO.getNearSportCenters(userSelectedSport);

        int numeroSportCenters = nearSportCenters.size();
        sportCenterList = new String[numeroSportCenters];

        int i = 0;

        for (String key : nearSportCenters.keySet()){
            sportCenterList[i] = key;
            i++;
        }

        BookMatchViewController c = BookMatchViewController.getBookMatchViewControllerInstance();
        c.disableButtons();
        c.enableScrollPane();
    }

    @Override
    public void displayView(){
        BookMatchViewController c = BookMatchViewController.getBookMatchViewControllerInstance();
        c.displaySportCenters(sportCenterList);
    }

    public String getUserSelectedSport(){
        return this.userSelectedSport;
    }
}
