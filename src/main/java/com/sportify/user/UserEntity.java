package com.sportify.user;

public class UserEntity {

    private String email;
    private String password;
    private String type;
    private UserPreferences preferences;
    private static UserEntity singleInstance = null;


    private UserEntity(){}

    public static UserEntity getInstance(){
        if (singleInstance == null)
            singleInstance = new UserEntity();
        return singleInstance;
    }

    public void setPreferences(UserPreferences p){
        this.preferences = p;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
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
