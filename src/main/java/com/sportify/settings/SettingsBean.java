package com.sportify.settings;

import com.sportify.settings.exceptions.AddressNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsBean {
    private int radius;
    private boolean notifications;
    private boolean football;
    private boolean padel;
    private boolean basket;
    private boolean tennis;
    private String address;
    private String city;
    private String cap;

    //TODO FARE BENE STA BEAN

    public void validateInput() throws AddressNotValidException {
        Pattern validCAP = Pattern.compile("^[0-9]{5}$"); //valida sintatticamente il CAP
        Matcher m = validCAP.matcher(this.cap);
        if (!m.find()){
            throw new IllegalArgumentException("Invalid ZIP code!");
        }
        /*
        Se il raggio non ha un valore valido (lo forza al valore di default)
         */
        if (this.radius != 3 && this.radius != 5 && this.radius != 10)
            radius = 5;

    }

    public int getRadius() {
        return radius;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public boolean isFootball() {
        return football;
    }

    public boolean isPadel() {
        return padel;
    }

    public boolean isBasket() {
        return basket;
    }

    public boolean isTennis() {
        return tennis;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCap() {
        return cap;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void setFootball(boolean football) {
        this.football = football;
    }

    public void setPadel(boolean padel) {
        this.padel = padel;
    }

    public void setBasket(boolean basket) {
        this.basket = basket;
    }

    public void setTennis(boolean tennis) {
        this.tennis = tennis;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
