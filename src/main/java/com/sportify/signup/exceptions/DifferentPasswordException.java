package com.sportify.signup.exceptions;

public class DifferentPasswordException extends Exception {
    public DifferentPasswordException() {
        super("*** Passwords are different, try again ***");
    }
}
