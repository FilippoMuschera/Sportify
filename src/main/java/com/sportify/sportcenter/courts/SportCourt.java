package com.sportify.sportcenter.courts;


import java.util.List;

public abstract class SportCourt {

    int courtID;
    String sport;
    List<TimeSlot> bookingTable;

    public int getCourtID() {
        return courtID;
    }

    public String getSport() {
        return sport;
    }

    public List<TimeSlot> getBookingTable() {
        return bookingTable;
    }

    public abstract void bookTimeSlot();




}
