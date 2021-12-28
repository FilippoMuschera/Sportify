package com.sportify.login.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("*** Utente non presente nel database ***");
    }
}
