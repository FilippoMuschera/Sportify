package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.GetSportCenterDAO;
import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();

    protected SportCenterState(){}

    @Override
    public void entry(String userSelectedSport){

        bookMatchController.setSelectedSport(userSelectedSport);

        Map<String, Double> nearSportCenters = GetSportCenterDAO.getInstance().getNearSportCenters(userSelectedSport);

        bookMatchController.setNearSportCentersMap(nearSportCenters);
    }
}
