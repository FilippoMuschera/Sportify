package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PadelCourt extends SportCourt{
    private static final String PADEL = "Padel";
    private static final int PADEL_SPOTS = 4;

    public PadelCourt(int id, List<LocalTime> timeSlots) {
        super.courtID = id;
        super.sport = PADEL;
        super.bookingTable = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i = i+2){
            super.bookingTable.add(new TimeSlot(timeSlots.get(i), timeSlots.get(i + 1), PADEL_SPOTS));

        }
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
