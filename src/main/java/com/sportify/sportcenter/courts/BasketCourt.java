package com.sportify.sportcenter.courts;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BasketCourt extends SportCourt{

    private static final String BASKET = "Basket";



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
        this.setMaxSpots();
        super.courtID = id;
        super.sport = BASKET;
        super.bookingTable = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            TimeSlot t = new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getAvailableSpots());
            super.bookingTable.add(t);
        }

    }

    private void setMaxSpots(){
        this.maxSpots = 10;
    }


}
