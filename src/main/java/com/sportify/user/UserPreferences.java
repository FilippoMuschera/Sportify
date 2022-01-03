package com.sportify.user;

import java.util.HashMap;
import java.util.Map;

public class UserPreferences {

    private Integer sortingDistance;        //TODO cambiare con le coordinate?
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public UserPreferences() {
        this.sortingDistance = 0;
        this.map.put("Basket",0);
        this.map.put("Calcio",0);
        this.map.put("Padel",0);
        this.map.put("Tennis",0);
    }

    public Integer getSortingDistance() {
        return sortingDistance;
    }

    public Map<String, Integer> getSports() {
        return map;
    }

}

