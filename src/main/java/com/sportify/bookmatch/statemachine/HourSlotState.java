package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import java.util.List;


public class HourSlotState implements BMStateInterface {

    private String[] hourSlotList;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();

    @Override
    public void entry(String court){

        List<TimeSlot> timeTable = bookMatchController.getBMTimeSlot();
        List<SportCourt> courtList = bookMatchController.getCourtList();

        int maxSpots;
        maxSpots = courtList.get(Integer.parseInt(court)).getMaxSpots();
        int i = 0;
        int numOfTimeSlot = timeTable.size();
        hourSlotList = new String[numOfTimeSlot];

        for(TimeSlot t: timeTable){
            if (t.getAvailableSpots() == maxSpots) {
                hourSlotList[i] = "" + t.getStartTime().getHour() + "-" + t.getEndTime().getHour();
                i++;
            }
        }
    }

    @Override
    public String[] getList(){
        return hourSlotList;
    }
}
