package com.sportify.login;

import com.sportify.login.exceptions.LoginFailedException;
import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;
import com.sportify.user.UserDAO;
import com.sportify.user.UserEntity;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

import java.util.Objects;

public class LogInController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";

    public void logInUser(LogInBean bean) throws UserNotFoundException, LoginFailedException {


        UserDAO dao = UserDAO.getInstance();

        dao.getUser(bean.getEmail());
        UserEntity user = UserEntity.getInstance();
        UserPreferencesDAO preferencesDAO = UserPreferencesDAO.getInstance();
        UserPreferences preferences = preferencesDAO.getUserPreferencesFromDB(bean.getEmail());
        user.setPreferences(preferences);

        String decryptedPassword = this.decryptPassword(user.getPassword());

        if (!Objects.equals(decryptedPassword, bean.getPassword()))
            throw new LoginFailedException("The password is incorrect");

    }

    private String decryptPassword(String psw) { //Viene passata la stringa criptata della password e non l'istanza
        //dell'user così il metodo non deve conoscere come è realizzata la
        //classe user
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(ENCRYPTION_KEY); //chiave per criptare la psw
        encryptor.setSaltGenerator(new ZeroSaltGenerator()); //Non utilizzare salt per l'encryption, così da avere
        //risultati uguali ogni volta che si cripta e decripta la
        //la stessa parola

        return encryptor.decrypt(psw);
    }


}