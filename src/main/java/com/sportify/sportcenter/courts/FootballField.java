package com.sportify.sportcenter.courts;

import java.util.List;

public class FootballField extends SportCourt{

    private static final String FOOTBALL = "Football";

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }

    public FootballField(int id, List<TimeSlot> timeSlots) {
        super.courtID = id;
        super.sport = FOOTBALL;
        super.bookingTable = timeSlots;
    }
}
