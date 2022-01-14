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

    public PadelCourt(List<TimeSlot> timeSlots, int id) {
        super.courtID = id;
        super.sport = PADEL;
        super.bookingTable = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            TimeSlot t = new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getAvailableSpots());
            super.bookingTable.add(t);
        }
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
