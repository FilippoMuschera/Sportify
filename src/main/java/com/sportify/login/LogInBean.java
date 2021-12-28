package com.sportify.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.login.exceptions.IncorrectPasswordException;
import com.sportify.login.exceptions.UserNotFoundException;

public class LogInBean {

    private String email = null;
    private String password = null;

    //Regex di una email valida, da usare per confrontarla con quella dell'utente
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public void setEmail(String username) {
        this.email = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void executeLogIn() throws EmailNotValidException, UserNotFoundException, IncorrectPasswordException {

        this.validateInput(); //Prima d'iniziare la procedura di login controlla la correttezza SINTATTICA dell'input

        //Se l'input è valido la classe Bean chiama il controller, che avrà la responsabilità di eseguire il logIn
        LogInController controller = new LogInController();
        controller.logInUser(this);


    }

    private void validateInput() throws IllegalArgumentException, EmailNotValidException{

        if (this.email == null || this.password == null ) //controlla se i suoi attributi sono stati correttamente
                                                          //inizializzati dalla LogInView
            throw new IllegalArgumentException("LogInBean class was not properly initialized for login"); //forse aggiungerla al throws?

        if (!validateEmailSyntax(this.email))
            throw new EmailNotValidException();
    }


    private boolean validateEmailSyntax(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
