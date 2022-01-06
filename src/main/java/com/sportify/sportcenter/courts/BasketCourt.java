package com.sportify.sportcenter.courts;

import java.util.List;

public class BasketCourt extends SportCourt{

    private static final String BASKET = "Basket";

    public BasketCourt(int id, List<TimeSlot> timeSlots) {
        super.courtID = id;
        super.sport = BASKET;
        super.bookingTable = timeSlots;
    }

    @Override
    public void bookTimeSlot() {
        //TODO implementazione dei metodi della classe astratta

    }
}
