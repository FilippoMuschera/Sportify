package com.sportify.joinmatch;

import com.sportify.sportcenter.courts.TimeSlot;

public class ResultElement {

    private String nameSC;
    private int courtID;
    private TimeSlot timeSlot;
    private double indexValue;
    private double distance;
    private int maxSpots;
    private String sport;

    public String getSport() {
        return sport;
    }

    public int getMaxSpots() {
        return maxSpots;
    }

    public double getDistance() {
        return distance;
    }

    public String getNameSC() {
        return nameSC;
    }

    public int getCourtID() {
        return courtID;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public double getIndexValue() {
        return indexValue;
    }

    public void setNameSC(String nameSC) {
        this.nameSC = nameSC;
    }

    public void setCourtID(int courtID) {
        this.courtID = courtID;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setIndexValue(double indexValue) {
        this.indexValue = indexValue;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
