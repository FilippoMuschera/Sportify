package com.sportify.cli;

import com.sportify.user.UserEntity;

public class ViewControllerCLI {
    private static ViewControllerCLI instance = null;
    private UserEntity user;

    private ViewControllerCLI(){}

    public static ViewControllerCLI getInstance(){
        if (ViewControllerCLI.instance == null)
            instance = new ViewControllerCLI();
        return instance;
    }

    public void setUser(UserEntity u){
        user = u;
    }

    public UserEntity getUser() {
        return user;
    }

    public void showCLIHomeScreen() {
        //TODO
        System.out.println("***WELCOME TO SPORTIFY CLI***");
    }
}
