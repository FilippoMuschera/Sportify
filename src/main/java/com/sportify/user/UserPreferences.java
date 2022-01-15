package com.sportify.user;

import java.util.HashMap;
import java.util.Map;
import com.sportify.user.observer.Subject;

public class UserPreferences extends Subject{

    private int radiusOfInterest;        
    private Map<String, Boolean> sportsMap = new HashMap<>();
    private boolean notifications;
    private String userAddress;
    private static final  String BASKET = "Basket";
    private static final String FOOTBALL = "Football";
    private static final String PADEL = "Padel";
    private static final String TENNIS = "Tennis";

    public UserPreferences(int radiusOfInterest, boolean b, boolean f, boolean p, boolean t, boolean n, String addr) {
        this.radiusOfInterest = radiusOfInterest;
        this.sportsMap.put(BASKET,b);
        this.sportsMap.put(FOOTBALL,f);
        this.sportsMap.put(PADEL,p);
        this.sportsMap.put(TENNIS,t);
        this.notifications = n;
        this.userAddress = addr;


    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setRadiusOfInterest(int radiusOfInterest) {
        this.radiusOfInterest = radiusOfInterest;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void setBasket(boolean b){
        this.sportsMap.put(BASKET, b);
    }
    
    public void setFootball(boolean f){
        this.sportsMap.put(FOOTBALL,f);

    }
    
    public void setPadel(boolean p){
        this.sportsMap.put(PADEL,p);

    }
    
    public void setTennis(boolean t){
        this.sportsMap.put(TENNIS,t);

    }
    
    public int getSortingDistance() {
        return this.radiusOfInterest;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public boolean getBasket() {
        return this.sportsMap.get(BASKET);
    }

    public boolean getFootball() {
        return this.sportsMap.get(FOOTBALL);
    }

    public boolean getTennis() {
        return this.sportsMap.get(TENNIS);
    }

    public boolean getPadel() {
        return this.sportsMap.get(PADEL);
    }

}

