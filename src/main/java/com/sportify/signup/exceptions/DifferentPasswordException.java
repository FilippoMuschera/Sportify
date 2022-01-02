package com.sportify.signup.exceptions;

public class DifferentPasswordException extends Exception {
    public DifferentPasswordException() {
        super("*** Le password non sono uguali ***");
    }
}
