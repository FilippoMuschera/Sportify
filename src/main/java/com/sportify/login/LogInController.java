package com.sportify.login;

import com.sportify.cli.ViewControllerCLI;
import com.sportify.login.exceptions.IncorrectPasswordException;
import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;
import com.sportify.utilitiesui.UIController;
import com.sportify.user.UserDAO;
import com.sportify.user.UserEntity;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

import java.util.Objects;

public class LogInController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";

    public void logInUser(LogInBean bean) throws UserNotFoundException, IncorrectPasswordException {


        UserDAO dao = UserDAO.getInstance();

        UserEntity user = dao.getUser(bean.getEmail());
        UserPreferencesDAO preferencesDAO = UserPreferencesDAO.getInstance();
        UserPreferences preferences = preferencesDAO.getUserPreferencesFromDB(bean.getEmail());
        user.setPreferences(preferences);

        String decryptedPassword = this.decryptPassword(user.getPassword());

        if (!Objects.equals(decryptedPassword, bean.getPassword()))
            throw new IncorrectPasswordException();
        StackWalker s = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        if (s.getCallerClass().equals(LogInView.class)) { // se viene chiamato dalla GUI interagisce con UIController
            UIController viewController = UIController.getUIControllerInstance();
            viewController.setUser(user);
        }
        else { //Se viene chiamato dalla CLI interagisce con ViewControllerCLI
            ViewControllerCLI.getInstance().setUser(user);
        }

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