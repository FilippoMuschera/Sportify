package com.sportify.sportcenter.courts;

import java.util.List;

public class TennisCourt extends SportCourt{

    private static final String TENNIS = "Tennis";

    public TennisCourt(int id, List<TimeSlot> timeSlots) {
        super.courtID = id;
        super.sport = TENNIS;
        super.bookingTable = timeSlots;
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
