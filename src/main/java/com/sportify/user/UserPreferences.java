package com.sportify.user;

import java.util.HashMap;
import java.util.Map;

public class UserPreferences {

    private int radiusOfInterest;        
    private Map<String, Boolean> sportsMap = new HashMap<String, Boolean>();
    private boolean notifications;

    public UserPreferences(int radiusOfInterest, boolean b, boolean f, boolean p, boolean t, boolean n) {
        this.radiusOfInterest = radiusOfInterest;
        this.sportsMap.put("Basket",b);
        this.sportsMap.put("Football",f);
        this.sportsMap.put("Padel",p);
        this.sportsMap.put("Tennis",t);
        this.notifications = n;


    }

    public Integer getSortingDistance() {
        return this.radiusOfInterest;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public Map<String, Boolean> getSports() {
        return this.sportsMap;
    }

}

