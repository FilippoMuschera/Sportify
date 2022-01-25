package com.sportify.settings;

import com.sportify.geolocation.Geolocator;
import com.sportify.settings.exceptions.AddressNotValidException;
import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;


public class SettingsController  {
    public void saveSettings(SettingsBean bean) throws AddressNotValidException{

        String userAddress = bean.getAddress() + ", " + bean.getCity() + ", " + bean.getCap();

        Geolocator g = Geolocator.getInstance();

       if (g.getLat(userAddress) == -1 || g.getLng(userAddress) == -1)
            throw new AddressNotValidException();

        UserEntity user = UserEntity.getInstance();
        UserPreferences preferences = user.getPreferences();
        preferences.setRadiusOfInterest(bean.getRadius());
        preferences.setNotifications(bean.isNotifications());
        preferences.setBasket(bean.isBasket());
        preferences.setFootball(bean.isFootball());
        preferences.setPadel(bean.isPadel());
        preferences.setTennis(bean.isTennis());

        preferences.setUserAddress(userAddress);

        UserPreferencesDAO dao = UserPreferencesDAO.getInstance();
        dao.saveUserPreferencesToDB(user.getEmail(), preferences, true);

        preferences.setState(true);

    }
}
