package com.sportify.signup;

import com.sportify.login.LogInBean;
import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.signup.exceptions.DifferentPasswordException;
import com.sportify.signup.exceptions.UserAlreadyExistsException;

import java.util.Objects;

public class SignUpBean extends LogInBean {

    private String firstPsw;
    private String secondPsw;
    private boolean isOwner;


    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public void setFirstPsw(String firstPsw) {
        this.firstPsw = firstPsw;
    }

    public void setSecondPsw(String secondPsw) {
        this.secondPsw = secondPsw;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void validateSignUp() throws EmailNotValidException, DifferentPasswordException {
        if(!Objects.equals(firstPsw, secondPsw))
            throw new DifferentPasswordException();
        else
            this.password = this.firstPsw;
        super.validateInput();

    }



}
