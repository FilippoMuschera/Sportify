package com.sportify.login;

import com.sportify.login.exceptions.IncorrectPasswordException;
import com.sportify.login.exceptions.UserNotFoundException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

import java.util.Objects;

public class LogInController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";

    public void logInUser(LogInBean bean) throws UserNotFoundException, IncorrectPasswordException {


        LogInDAOSafe dao = LogInDAOSafe.getInstance();

        UserLogInEntity user = dao.getUser(bean.getEmail());

        String decryptedPassword = this.decryptPassword(user.getPassword());

        if (!Objects.equals(decryptedPassword, bean.getPassword()))
            throw new IncorrectPasswordException();

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