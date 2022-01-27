package com.sportify.login.exceptions;

public class EmailNotValidException extends Exception{

    public EmailNotValidException(){
        super("Invalid email! Try again!");
    }

}
