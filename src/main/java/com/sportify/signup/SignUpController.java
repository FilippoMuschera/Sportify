package com.sportify.signup;

import com.sportify.signup.exceptions.DifferentPasswordException;
import com.sportify.signup.exceptions.UserAlreadyExistsException;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;
import com.sportify.user.UserDAO;
import com.sportify.user.UserEntity;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class SignUpController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";
    private static final String DEFAULT_ADDRESS = "Viale degli Ingegneri 1, Roma, 00133";
    private static final int DEFAULT_RADIUS = 5;
    private static final boolean DEFAULT_PREFERENCE = true;

    public void signUpUser(SignUpBean bean) throws UserAlreadyExistsException, DifferentPasswordException {
        UserDAO dao = UserDAO.getInstance();
        String checkedPassword;
        if (!bean.getFirstPsw().equals(bean.getSecondPsw()))
            throw new DifferentPasswordException();
        checkedPassword = bean.getFirstPsw(); //Se sono uguali è indifferente scegliere la prima o la seconda
        String encryptedPassword = this.encryptPassword(checkedPassword);
        String type = bean.isOwner() ? "Owner" : "Player";
        dao.addUser(bean.getEmail(), encryptedPassword, type);
        UserEntity user = UserEntity.getInstance();
        UserPreferences preferences = new UserPreferences(DEFAULT_RADIUS, DEFAULT_PREFERENCE, DEFAULT_PREFERENCE,
                DEFAULT_PREFERENCE, DEFAULT_PREFERENCE, DEFAULT_PREFERENCE, DEFAULT_ADDRESS);
        UserPreferencesDAO daoPreferences = UserPreferencesDAO.getInstance();
        daoPreferences.saveUserPreferencesToDB(user.getEmail(), preferences, false);

        user.setPreferences(preferences);


    }


    private String encryptPassword(String psw) { //Viene passata la stringa da criptare
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(ENCRYPTION_KEY); //chiave per criptare la psw
        encryptor.setSaltGenerator(new ZeroSaltGenerator()); //Non utilizzare salt per l'encryption, così da avere
        //risultati uguali ogni volta che si cripta e decripta la
        //la stessa parola

        return encryptor.encrypt(psw);
    }
}
