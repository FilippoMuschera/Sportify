package com.sportify.sportcenter;

import java.time.LocalTime;

public class SportCenterTime {

    private final LocalTime openingTime;
    private final LocalTime closingTime;

    SportCenterTime (int openH, int openM, int closingH, int closingM){
        this.openingTime = LocalTime.of(openH, openM);
        this.closingTime = LocalTime.of(closingH, closingM);
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }
}
