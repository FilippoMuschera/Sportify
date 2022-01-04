package com.sportify.user;

public class UserEntity {

    private String email;
    private String password;
    private String type;
    private UserPreferences preferences;

    public UserEntity(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public void setPreferences(UserPreferences p){
        this.preferences = p;
    }

    public UserPreferences getPreferences() {
        return preferences;
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
