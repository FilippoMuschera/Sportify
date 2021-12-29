package com.sportify.login;

public class UserLogInEntity {

    private String email;
    private String password;
    private String type;

    public UserLogInEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLogInEntity(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }
}
