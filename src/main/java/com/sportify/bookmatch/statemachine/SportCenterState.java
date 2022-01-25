package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.geolocation.Geolocator;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.user.UserEntity;

import java.util.Map;


public class SportCenterState implements BMStateInterface {

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private static final int MAX_NUMBER_OF_RESULTS = 3;

    protected SportCenterState(){}

    @Override
    public void entry(String userSelectedSport){

        bookMatchController.setSelectedSport(userSelectedSport);

        String userAddress = UserEntity.getInstance().getPreferences().getUserAddress();
        Geolocator g = Geolocator.getInstance();
        double lat = g.getLat(userAddress);
        double lng = g.getLng(userAddress);



        Map<String, Double> nearSportCenters = GetSportCenterDAO.getInstance().getNearSportCenters(userSelectedSport, MAX_NUMBER_OF_RESULTS, lat, lng);

        bookMatchController.setNearSportCentersMap(nearSportCenters);
    }
}
