package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BasketCourt extends SportCourt{

    private static final String BASKET = "Basket";

    public static int getMaxSpots(){
        return maxSpots;
    }

    public BasketCourt(int id, List<LocalTime> timeSlots) {
        this.setMaxSpots();
        super.courtID = id;
        super.sport = BASKET;
        super.bookingTable = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i = i+2){
            super.bookingTable.add(new TimeSlot(timeSlots.get(i), timeSlots.get(i + 1), maxSpots));

        }
    }

    public BasketCourt(List<TimeSlot> timeSlots, int id) {
        super.courtID = id;
        super.sport = BASKET;
        super.bookingTable = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            TimeSlot t = new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getAvailableSpots());
            super.bookingTable.add(t);
        }

    }

    private void setMaxSpots(){
        maxSpots = 10;
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
