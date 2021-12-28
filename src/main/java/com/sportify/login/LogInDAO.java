package com.sportify.login;

import com.sportify.login.exceptions.UserNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;




public class LogInDAO {

    private static LogInDAO instance = null;

    public static LogInDAO getInstance(){
        if (LogInDAO.instance == null)
            return new LogInDAO();
        else {
            LogInDAO.instance = new LogInDAO();
            return LogInDAO.instance;
        }
    }

    private LogInDAO(){}

    public UserLogInEntity getUser(String email) throws UserNotFoundException { //AGGIUNGERE CASO IN CUI IL RESULT SET è VUOTO

        try (Connection con = getConnector()) {

            if (con == null)
                throw new SQLException();
           try (Statement statement = con.createStatement()) {
               String query = "Select Email, Password FROM Users WHERE Email = '" + email + "';";
               ResultSet res = statement.executeQuery(query);
               if (!res.next()) //Posizionamento sul risultato della query e verifica se il risultato è vuoto
                   throw new UserNotFoundException();
               return new UserLogInEntity(res.getString("Email"), res.getString("Password"));
           }
        } catch (SQLException e){
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
