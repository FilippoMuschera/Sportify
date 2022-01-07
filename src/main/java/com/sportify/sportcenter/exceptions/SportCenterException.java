package com.sportify.sportcenter.exceptions;

public class SportCenterException extends RuntimeException{
    public SportCenterException(){
        super("Error adding SC to the DB. Check the log");
    }
}
