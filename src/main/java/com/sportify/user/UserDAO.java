package com.sportify.user;

import com.sportify.login.exceptions.UserNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;




public class UserDAO {

    private static UserDAO instance = null;

    public static UserDAO getInstance(){
        if (UserDAO.instance == null)
            return new UserDAO();
        else {
            UserDAO.instance = new UserDAO();
            return UserDAO.instance;
        }
    }

    private UserDAO(){}

    public UserEntity getUser(String email) throws UserNotFoundException {

        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "SELECT Email, Password, Type FROM Users WHERE Email = ?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                if (!rs.next())
                    throw new UserNotFoundException();
                UserEntity user = new UserEntity(rs.getString("Email"), rs.getString("Password"), rs.getString("Type"));
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
