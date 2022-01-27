package com.sportify.signup;

import com.sportify.login.LogInBean;
import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.signup.exceptions.DifferentPasswordException;

import java.util.Objects;

public class SignUpBean extends LogInBean {

    private String firstPsw;
    private String secondPsw;
    private boolean isOwner;


    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public void setFirstPsw(String firstPsw) {
        if (firstPsw.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        this.firstPsw = firstPsw;
    }

    public void setSecondPsw(String secondPsw) {
        if (secondPsw.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        this.secondPsw = secondPsw;
    }

    public boolean isOwner() {
        return isOwner;
    }


    public String getFirstPsw() {
        return firstPsw;
    }

    public String getSecondPsw() {
        return secondPsw;
    }
}




