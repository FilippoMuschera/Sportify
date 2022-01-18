package com.sportify.sportcenter.courts;

import java.time.LocalTime;

public class TimeSlot {
    public static final int DEFAULT_SLOT_DURATION = 2; //in hour
    private LocalTime startTime;
    private LocalTime endTime;
    private int availableSpots;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(LocalTime startTime, LocalTime endTime, int availableSpots) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSpots = availableSpots;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }
}
