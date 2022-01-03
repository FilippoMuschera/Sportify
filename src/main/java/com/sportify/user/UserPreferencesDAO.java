package com.sportify.user;

public class UserPreferencesDAO {
    private static UserPreferencesDAO instance = null;

    public static UserPreferencesDAO getInstance(){
        if (UserPreferencesDAO.instance == null)
            return new UserPreferencesDAO();
        else {
            UserPreferencesDAO.instance = new UserPreferencesDAO();
            return UserPreferencesDAO.instance;
        }
    }

    private UserPreferencesDAO(){}

    //TODO implementare tutto
}
