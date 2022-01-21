package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FootballField extends SportCourt{

    private static final String FOOTBALL = "Football";


    public FootballField(List<TimeSlot> timeSlots, int id) {
        this.setMaxSpots();
        super.courtID = id;
        super.sport = FOOTBALL;
        super.bookingTable = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            TimeSlot t = new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getAvailableSpots());
            super.bookingTable.add(t);
        }

        }

    public FootballField(int id, List<LocalTime> timeSlots) {
        this.setMaxSpots();
        super.courtID = id;
        super.sport = FOOTBALL;
        super.bookingTable = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i = i + 2) {
            super.bookingTable.add(new TimeSlot(timeSlots.get(i), timeSlots.get(i + 1), maxSpots));

        }
    }

    private void setMaxSpots(){
        maxSpots = 10;
    }


}
