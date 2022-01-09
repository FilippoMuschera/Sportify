package com.sportify.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sportify.utilitydb.DBConnector.getConnector;

public class UserPreferencesDAO {

    private static UserPreferencesDAO instance = null;

    public static UserPreferencesDAO getInstance(){
        if (UserPreferencesDAO.instance == null)
            UserPreferencesDAO.instance = new UserPreferencesDAO();
        return UserPreferencesDAO.instance;
    }

    private UserPreferencesDAO(){}

    public UserPreferences getUserPreferencesFromDB(String email){
        try  {
            Connection con = getConnector();
            if (con == null)
                throw new SQLException();
            String query = "SELECT * FROM UsersPreferences WHERE Email = ?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next(); //apro il risultato della query
                UserPreferences preferences = new UserPreferences(rs.getInt("InterestRadius"), rs.getBoolean("Basket"),
                        rs.getBoolean("Football"), rs.getBoolean("Padel"), rs.getBoolean("Tennis"),
                        rs.getBoolean("Notifications"), rs.getString("Address"));
                rs.close();
                return preferences;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    public void saveUserPreferencesToDB(String email, UserPreferences preferences, boolean isAnUpdate){
        try  {
            Connection con = getConnector();

            String queryUpdate = "UPDATE `sportify_db`.`UsersPreferences` SET `InterestRadius` = ?, `Notifications` = ?, `Football` = ?, `Padel` = ?, `Basket` = ?, `Tennis` = ?, `Address` = ? WHERE (`Email` = ?);";
            String queryInsert = "INSERT INTO `sportify_db`.`UsersPreferences` (`InterestRadius`, `Notifications`, `Football`, `Padel`, `Basket`, `Tennis`, `Address`, `Email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            String query = (isAnUpdate ? queryUpdate : queryInsert);
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, preferences.getSortingDistance());
                preparedStatement.setBoolean(2, preferences.isNotifications());
                preparedStatement.setBoolean(3, preferences.getFootball());
                preparedStatement.setBoolean(4, preferences.getPadel());
                preparedStatement.setBoolean(5, preferences.getBasket());
                preparedStatement.setBoolean(6, preferences.getTennis());
                preparedStatement.setString(7, preferences.getUserAddress());
                preparedStatement.setString(8, email);

                preparedStatement.executeUpdate();


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


}
