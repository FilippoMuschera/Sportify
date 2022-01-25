package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private static final int MAX_NUMBER_OF_RESULTS = 3;

    protected SportCenterState(){}

    @Override
    public void entry(String userSelectedSport){

        bookMatchController.setSelectedSport(userSelectedSport);

        Map<String, Double> nearSportCenters = GetSportCenterDAO.getInstance().getNearSportCenters(userSelectedSport, MAX_NUMBER_OF_RESULTS);

        bookMatchController.setNearSportCentersMap(nearSportCenters);
    }
}
