package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FootballField extends SportCourt{

    private static final String FOOTBALL = "Football";
    private static final int FOOTBALL_SPOTS = 10;

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }

    public FootballField(int id, List<LocalTime> timeSlots) {
        super.courtID = id;
        super.sport = FOOTBALL;
        super.bookingTable = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i = i+2){
            super.bookingTable.add(new TimeSlot(timeSlots.get(i), timeSlots.get(i + 1), FOOTBALL_SPOTS));

        }

    }
}
