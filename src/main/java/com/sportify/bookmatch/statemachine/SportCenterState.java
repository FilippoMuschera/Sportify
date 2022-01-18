package com.sportify.bookmatch.statemachine;

import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private String userSelectedSport;
    private String[] sportCenterList;
    private Map<String, Double> nearSportCenters;

    private static SportCenterState singleSportCenterStateInstance = null;

    protected SportCenterState(){}

    public static SportCenterState getSportCenterStateInstance(){
        if (SportCenterState.singleSportCenterStateInstance == null){
            SportCenterState.singleSportCenterStateInstance = new SportCenterState();
        }
        return SportCenterState.singleSportCenterStateInstance;
    }

    @Override
    public void entry(String userSelectedSport){

        this.userSelectedSport = userSelectedSport;

        nearSportCenters = GetSportCenterDAO.getInstance().getNearSportCenters(userSelectedSport);

        int numeroSportCenters = nearSportCenters.size();
        sportCenterList = new String[numeroSportCenters];

        int i = 0;

        for (String key : nearSportCenters.keySet()){
            sportCenterList[i] = key;
            i++;
        }

    }

    public String getUserSelectedSport(){
        return this.userSelectedSport;
    }

    @Override
    public String[] getList(){
        return this.sportCenterList;
    }
}
