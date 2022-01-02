package com.sportify.user;

public class UserEntity {

    private String email;
    private String password;
    private String type;

    public UserEntity(String email, String password, String type) {
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
