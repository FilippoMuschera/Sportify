package com.sportify.login;

import com.sportify.login.exceptions.UserNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;




public class LogInDAOSafe {

    private static LogInDAOSafe instance = null;

    public static LogInDAOSafe getInstance(){
        if (LogInDAOSafe.instance == null)
            return new LogInDAOSafe();
        else {
            LogInDAOSafe.instance = new LogInDAOSafe();
            return LogInDAOSafe.instance;
        }
    }

    private LogInDAOSafe(){}

    public UserLogInEntity getUser(String email) throws UserNotFoundException { //AGGIUNGERE CASO IN CUI IL RESULT SET è VUOTO

        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "SELECT Email, Password, Type FROM Users WHERE Email = ?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                if (!rs.next())
                    throw new UserNotFoundException();
                UserLogInEntity user = new UserLogInEntity(rs.getString("Email"), rs.getString("Password"), rs.getString("Type"));
                rs.close();
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnector() throws SQLException {
        Connection con = null;

        try (InputStream input = new FileInputStream("src/main/resources/com.sportify.login/DBOnline.properties")) {

            Properties prop = new Properties();

            // carica il file properties
            prop.load(input);
            //ritorna la connessione al DB specificato nel file DB.properties
            con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.psw"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return con;
    }

}
