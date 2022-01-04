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
                        rs.getBoolean("Notifications"));
                rs.close();
                return preferences;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }


}
