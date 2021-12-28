package com.sportify.login;

public class UserLogInEntity {

    private String email;
    private String password;

    public UserLogInEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
