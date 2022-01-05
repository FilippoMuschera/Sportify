package com.sportify.settings;

import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;
import com.sportify.utilitiesui.UIController;

public class SettingsController {
    public void saveSettings(SettingsBean bean) {

        UserEntity user = UIController.getUIControllerInstance().getUser();
        UserPreferences preferences = user.getPreferences();
        preferences.setRadiusOfInterest(bean.getRadius());
        preferences.setNotifications(bean.isNotifications());
        preferences.setBasket(bean.isBasket());
        preferences.setFootball(bean.isFootball());
        preferences.setPadel(bean.isPadel());
        preferences.setTennis(bean.isTennis());
        String userAddress = bean.getAddress() + ", " + bean.getCity() + ", " + bean.getCap();
        //TODO check indirizzo first of all
        preferences.setUserAddress(userAddress);

        UserPreferencesDAO dao = UserPreferencesDAO.getInstance();
        dao.saveUserPreferencesToDB(user.getEmail(), preferences, true);

    }
}
