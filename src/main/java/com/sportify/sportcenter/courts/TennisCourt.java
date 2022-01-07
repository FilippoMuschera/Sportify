package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TennisCourt extends SportCourt{

    private static final String TENNIS = "Tennis";
    private static final int TENNIS_SPOTS = 4;

    public TennisCourt(int id, List<LocalTime> timeSlots) {
        super.courtID = id;
        super.sport = TENNIS;
        super.bookingTable = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i = i+2){
            super.bookingTable.add(new TimeSlot(timeSlots.get(i), timeSlots.get(i + 1), TENNIS_SPOTS));

        }
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
