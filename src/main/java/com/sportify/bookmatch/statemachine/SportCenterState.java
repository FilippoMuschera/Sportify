package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private String userSelectedSport;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private static SportCenterState singleSportCenterStateInstance = null;
    private Map<String, Double> nearSportCenters;

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

        nearSportCenters = GetSportCenterDAO.getInstance().getNearSportCenters(userSelectedSport);

        bookMatchController.setNearSportCentersMap(nearSportCenters);
    }
}
