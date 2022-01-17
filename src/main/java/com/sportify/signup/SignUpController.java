package com.sportify.signup;

import com.sportify.cli.ViewControllerCLI;
import com.sportify.signup.exceptions.UserAlreadyExistsException;
import com.sportify.user.UserPreferences;
import com.sportify.user.UserPreferencesDAO;
import com.sportify.utilitiesui.UIController;
import com.sportify.user.UserDAO;
import com.sportify.user.UserEntity;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class SignUpController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";
    private static final String DEFAULT_ADDRESS = "Viale degli Ingegneri 1, Roma, 00133";
    private static final int DEFAULT_RADIUS = 5;
    private static final boolean DEFAULT_PREFERENCE = true;

    public void signUpUser(SignUpBean bean) throws UserAlreadyExistsException {
        UserDAO dao = UserDAO.getInstance();
        String encryptedPassword = this.encryptPassword(bean.getPassword());
        String type = bean.isOwner() ? "Owner" : "Player";
        UserEntity user = dao.addUser(bean.getEmail(), encryptedPassword, type);
        UserPreferences preferences = new UserPreferences(DEFAULT_RADIUS, DEFAULT_PREFERENCE, DEFAULT_PREFERENCE,
                DEFAULT_PREFERENCE, DEFAULT_PREFERENCE, DEFAULT_PREFERENCE, DEFAULT_ADDRESS);
        UserPreferencesDAO daoPreferences = UserPreferencesDAO.getInstance();
        daoPreferences.saveUserPreferencesToDB(user.getEmail(), preferences, false);

        user.setPreferences(preferences);

        //Se non vengono generate eccezioni allora viene eseguito il codice sottostante

        //Si usa la stessa logica del login per settare correttamente l'utente
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        if (walker.getCallerClass().equals(SignUpView.class)) {
            UIController viewController = UIController.getUIControllerInstance();
            viewController.setUser(user);
        }
        else {
            ViewControllerCLI.getInstance().setUser(user);
        }

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
