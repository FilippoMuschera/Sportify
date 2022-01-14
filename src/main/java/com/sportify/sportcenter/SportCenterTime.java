package com.sportify.sportcenter;

import java.time.LocalTime;

public class SportCenterTime {

    private final LocalTime openingTime;
    private final LocalTime closingTime;

    public SportCenterTime(int openH, int closingH){
        this.openingTime = LocalTime.of(openH, 0); //tutti gli orari sono interi (es.: 10:00)
        this.closingTime = LocalTime.of(closingH, 0); //tutti gli orari sono interi (es.: 10:00)
    }



    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }
}
