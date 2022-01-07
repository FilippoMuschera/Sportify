package com.sportify.sportcenter;

import com.sportify.sportcenter.courts.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SportCenterCourts {

    private ArrayList<SportCourt> footballFields;
    private ArrayList<SportCourt> padelCourts;
    private ArrayList<SportCourt> basketCourts;
    private ArrayList<SportCourt> tennisCourts;

    public List<SportCourt> getFootballFields() {
        return footballFields;
    }

    public List<SportCourt> getPadelCourts() {
        return padelCourts;
    }

    public List<SportCourt> getBasketCourts() {
        return basketCourts;
    }

    public List<SportCourt> getTennisCourts() {
        return tennisCourts;
    }

    public SportCenterCourts(int numOfFootballFields, int numOfPadelCourts, int numOfBasketCourts, int numOfTennisCourts,
                             List<LocalTime> timeSlots) {
        this.footballFields = new ArrayList<>();
        for (int i = 0; i < numOfFootballFields; i++) {
            footballFields.add(new FootballField(i, timeSlots));
        }
        this.padelCourts = new ArrayList<>();
        for (int i = 0; i < numOfPadelCourts; i++) {
            padelCourts.add(new PadelCourt(i, timeSlots));
        }
        this.basketCourts = new ArrayList<>();
        for (int i = 0; i < numOfBasketCourts; i++) {
            basketCourts.add(new BasketCourt(i, timeSlots));
        }
        this.tennisCourts = new ArrayList<>();
        for (int i = 0; i < numOfTennisCourts; i++) {
            tennisCourts.add(new TennisCourt(i, timeSlots));
        }



    }
}
