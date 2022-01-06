package com.sportify.sportcenter;

import com.sportify.sportcenter.courts.*;

import java.util.ArrayList;
import java.util.List;

public class SportCenterCourts {

    private ArrayList<FootballField> footballFields;
    private ArrayList<PadelCourt> padelCourts;
    private ArrayList<BasketCourt> basketCourts;
    private ArrayList<TennisCourt> tennisCourts;

    public List<FootballField> getFootballFields() {
        return footballFields;
    }

    public List<PadelCourt> getPadelCourts() {
        return padelCourts;
    }

    public List<BasketCourt> getBasketCourts() {
        return basketCourts;
    }

    public List<TennisCourt> getTennisCourts() {
        return tennisCourts;
    }

    public SportCenterCourts(int numOfFootballFields, int numOfPadelCourts, int numOfBasketCourts, int numOfTennisCourts,
                             List<TimeSlot> timeSlots) {
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
