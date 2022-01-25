package com.sportify.cli;

import com.sportify.bookmatch.BookMatchController;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;

public class BookMatchCLI {

    private BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
    private UserPreferences userPreferences =  UserEntity.getInstance().getPreferences();
    private static final String ERROR_MESSAGE = "Invalid option, try again.";

    public void startBookMatch(){
        int selectedSport;
        while ((selectedSport = showSports()) < 0) {
            //***mi raccomando copia ma cambia un pochino*** meme
        }
        switch(selectedSport){
            case 1:
                if(!userPreferences.getBasket()){
                    err.println("If you want to book a basket match, please select the sport in your settings.");
                    CLIController c = CLIController.getIstance();
                    c.showSettings();
                }
                selectSportCenter("Basket");
                break;
            case 2:
                if(!userPreferences.getFootball()){
                    err.println("If you want to book a football match, please select the sport in your settings.");
                    CLIController c = CLIController.getIstance();
                    c.showSettings();
                }
                selectSportCenter("Football");
                break;
            case 3:
                if(!userPreferences.getPadel()){
                    err.println("If you want to book a padel match, please select the sport in your settings.");
                    CLIController c = CLIController.getIstance();
                    c.showSettings();
                }
                selectSportCenter("Padel");
                break;
            case 4:
                if(!userPreferences.getTennis()){
                    err.println("If you want to book a tennis match, please select the sport in your settings.");
                    CLIController c = CLIController.getIstance();
                    c.showSettings();
                }

                selectSportCenter("Tennis");
                break;
            case 5:
                System.exit(0);
                break;
            default:
        }

    }

    private int showSports() {
        out.println("""
                                        
                
                Select the sport you interested in (insert the number):
                1. Basket
                2. Football
                3. Padel
                4. Tennis
                5. Quit the application""");

        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > 5 || selectedOption < 1) {
            err.println(ERROR_MESSAGE);
            return -1;
        }

        return selectedOption;
    }

    private void selectSportCenter(String sportName){
        Map<String, Double> sportCenters = bookMatchController.startStateMachine(sportName);
        int selectedSportCenterIndex;
        String selectedSportCenter = "";
        while((selectedSportCenterIndex = showSportCenters(sportCenters)) < 0){
            //aspetto input corretto
        }
        int i = 1;
        for (Iterator<String> iterator = sportCenters.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            if(i == selectedSportCenterIndex){
                selectedSportCenter = key;
            }
            i++;
        }
        selectCourt(selectedSportCenter);
    }

    private int showSportCenters(Map<String,Double> sportCenters){
        out.println("""
                
                
                """);
        out.println("Select the sport center you interested in. (Please insert a number)");
        int i = 1;
        for (Iterator<String> iterator = sportCenters.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            out.println(i + ". " + key + " (" + new DecimalFormat("##.##").format(sportCenters.get(key)) + " kms away)");
            i++;
        }
        i--;
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > i || selectedOption < 1) {
            err.println(ERROR_MESSAGE);
            return -1;
        }
        return selectedOption;
    }

    private void selectCourt(String selectedSportCenter){
        List<SportCourt> courtsList = bookMatchController.selectedSportCenter(selectedSportCenter);
        int selectedCourtIndex;
        while((selectedCourtIndex = showCourts(courtsList)) < 0){
            //aspetto input corretto
        }
        selectedCourtIndex--;
        selectTimeSlot(selectedCourtIndex);
    }

    private int showCourts(List<SportCourt> courtsList){
        int i = 1;
        out.println("""
                
                
                """);
        out.println("Select the court you want (Please insert a number):");
        for(SportCourt s: courtsList){
            out.println(i+". Court number: "+s.getCourtID());
            i++;
        }
        i--;
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > i || selectedOption < 1) {
            err.println(ERROR_MESSAGE);
            return -1;
        }
        return selectedOption;
    }

    private void selectTimeSlot(int selectedCourtIndex){
        List<TimeSlot> timeTable = bookMatchController.selectedCourt(String.valueOf(selectedCourtIndex));
        int selectedTimeSlotIndex;
        while ((selectedTimeSlotIndex = showTimeTable(timeTable)) < 0){
            //aspetto input corretto
        }
        TimeSlot selectedTimeSlot = null;
        int i = 1;
        for (TimeSlot t: timeTable){
            if(i == selectedTimeSlotIndex){
                selectedTimeSlot = t;
            }
            i++;
        }
        selectOperation(selectedTimeSlot);
    }

    private int showTimeTable(List<TimeSlot> timeTable){
        int i = 1;
        out.println("""
                
                
                """);
        out.println("Select the time slot you prefer (please insert a number):");
        for(TimeSlot t: timeTable){
            int start = t.getStartTime().getHour();
            int finish = t.getEndTime().getHour();
            int spots = t.getAvailableSpots();
            out.println(i+". Start time: "+start+" Finish time: "+finish+" Spots: "+spots);
            i++;
        }
        i--;
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > i || selectedOption < 1) {
            err.println(ERROR_MESSAGE);
            return -1;
        }
        return selectedOption;
    }

    private void selectOperation(TimeSlot selectedTimeSlot){
        int selectedOperation;
        bookMatchController.setSelectedTimeSlot(selectedTimeSlot);
        while ((selectedOperation = showOperation()) < 0){
            //aspetto input corretto
        }
        switch(selectedOperation){
            case 1:
                bookMatchController.bookMatch();
                break;
            case 2:
                bookMatchController.createJoinMatch();
                break;
            default:
        }
        HomeScreenCLI c = HomeScreenCLI.getInstance();
        c.showCLIHomeScreen();
    }

    private int showOperation(){
        out.println("""
                
                
                
                Do you want to book the match or create a joinable match?
                
                1. Book match.
                2. Create a joinable match.
                """);
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > 2 || selectedOption < 1) {
            err.println(ERROR_MESSAGE);
            return -1;
        }
        return selectedOption;
    }
}
