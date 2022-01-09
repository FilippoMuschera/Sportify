package com.sportify.user;

import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.signup.exceptions.UserAlreadyExistsException;

import java.sql.*;

import static com.sportify.utilitydb.DBConnector.getConnector;


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

        try  {
            Connection con = getConnector();
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

    public UserEntity addUser(String email, String password, String type) throws UserAlreadyExistsException {
        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "INSERT INTO `sportify_db`.`Users` (`Email`, `Password`, `Type`) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, type);
                preparedStatement.executeUpdate();
                return new UserEntity(email, password, type);

            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserAlreadyExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }



}
