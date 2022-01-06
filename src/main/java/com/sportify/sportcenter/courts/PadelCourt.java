package com.sportify.sportcenter.courts;

import java.util.List;

public class PadelCourt extends SportCourt{
    private static final String PADEL = "Padel";

    public PadelCourt(int id, List<TimeSlot> timeSlots) {
        super.courtID = id;
        super.sport = PADEL;
        super.bookingTable = timeSlots;
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
