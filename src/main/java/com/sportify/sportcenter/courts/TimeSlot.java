package com.sportify.sportcenter.courts;

import java.time.LocalTime;

public class TimeSlot {
    public static final int DEFAULT_SLOT_DURATION = 2; //in hour
    boolean isBooked;
    LocalTime startTime;
    LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
