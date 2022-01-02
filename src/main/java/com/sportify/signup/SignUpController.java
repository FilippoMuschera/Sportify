package com.sportify.signup;

import com.sportify.signup.exceptions.UserAlreadyExistsException;
import com.sportify.sportifyui.UIController;
import com.sportify.user.UserDAO;
import com.sportify.user.UserEntity;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class SignUpController {

    private static final String ENCRYPTION_KEY = "ISPW_PROJECT_SPORTIFY_2022";

    public void signUpUser(SignUpBean bean) throws UserAlreadyExistsException {
        UserDAO dao = UserDAO.getInstance();
        String encryptedPassword = this.encryptPassword(bean.getPassword());
        String type = bean.isOwner() ? "Owner" : "Player";
        UserEntity user = dao.addUser(bean.getEmail(), encryptedPassword, type);

        //Se non vengono generate eccezioni allora viene eseguito il codice sottostante
        UIController viewController = UIController.getUIControllerInstance();
        viewController.setUser(user);
        //Il controller viene quindi ritornato al viewController che aggiorna l'interfaccia

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
