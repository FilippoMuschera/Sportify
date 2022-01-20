package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private String userSelectedSport;
    private String[] sportCenterList;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
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
        bookMatchController.setSelectedSport(userSelectedSport);
        Map<String, Double> nearSportCenters = bookMatchController.getNearSportCenters(userSelectedSport);

        int numeroSportCenters = nearSportCenters.size();
        sportCenterList = new String[numeroSportCenters];

        int i = 0;

        for (String key : nearSportCenters.keySet()){
            sportCenterList[i] = key;
            i++;
        }

    }

    @Override
    public String[] getList(){
        return this.sportCenterList;
    }
}
