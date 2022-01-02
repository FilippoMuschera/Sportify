package com.sportify.signup.exceptions;

public class UserAlreadyExistsException extends Exception {
       public UserAlreadyExistsException(){
            super("*** Utente già registrato ***");
        }
}
