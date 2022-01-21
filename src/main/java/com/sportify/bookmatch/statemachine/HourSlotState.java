package com.sportify.bookmatch.statemachine;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import java.util.List;


public class HourSlotState implements BMStateInterface {

    private String[] hourSlotList;
    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private List<TimeSlot> timeTable;

    @Override
    public void entry(String court){

        int maxCourtSpot = bookMatchController.getCourtList().get(Integer.parseInt(court)).getMaxSpots();
        bookMatchController.setMaxCourtSpot(maxCourtSpot);
        bookMatchController.setSelectedCourtID(Integer.valueOf(court));

        List<SportCourt> courtList = bookMatchController.getCourtList();

        timeTable = courtList.get(bookMatchController.getSelectedCourtID()).getBookingTable();
        bookMatchController.setTimeTable(timeTable);
    }
}
