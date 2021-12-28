package com.sportify.login.exceptions;

public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException(){
        super("*** Password Errata ***");
    }

}
