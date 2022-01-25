package com.sportify.joinmatch;

import com.sportify.geolocation.Geolocator;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;

import java.util.*;

public class JoinMatchController {

    private static final int MAX_RESULTSET_DIMENSION = 5;

    public List<TimeSlot> getJoinableMatch(String sport){
        /*
         * Questo metodo ha lo scopo di fornire all'utente dei joinable match, ovvero dei TimeSlot (che rappresentano
         * delle prenotazioni) i cui posti disponibili sono ancora maggiori di zero. I posti disponibili mostrati saranno
         * ordinati a seconda della distanza dal centro sportivo dell'utente, e saranno anche scelti in base al numero
         * di posti ancora disponibili. Si vuole favorire i TimeSlot con meno posti disponibili, in modo da favorire il
         * riempimento di questi ultimi, e avere più giocatori che convergono sulle stesse partite, piuttosto che avere
         * tanti campi ognuno con poche prenotazioni.
         */

        Geolocator g = Geolocator.getInstance();
        double userLat = g.getLat(UserEntity.getInstance().getPreferences().getUserAddress());
        double userLng = g.getLng(UserEntity.getInstance().getPreferences().getUserAddress());

        GetSportCenterDAO dao = GetSportCenterDAO.getInstance();
        Map<String, Double> sportCenters = dao.getNearSportCenters(sport, 3, userLat, userLng);

        ArrayList<SportCenterEntity> sportCenterList = new ArrayList<>();
        ArrayList<TimeSlot> timeSlotList = new ArrayList<>();


        sportCenters.keySet().forEach(sc -> {
            sportCenterList.add(dao.getSportCenter(sc, sport));
            System.out.println("Sport Center: " + sc + ", distance: " + sportCenters.get(sc));
        });

        //NON ANDREBBERO AGGIUNTI I TIMESLOT CON 0 POSTI DISPONIBILI, MA TANTO QUESTO METODO VA CAMBIATO

        switch (sport){
            case "Football":
                for (SportCenterEntity sportCenter : sportCenterList){
                    for (SportCourt court : sportCenter.getCourts().getFootballFields()){
                        timeSlotList.addAll(court.getBookingTable());
                    }
                }
                break;
            case "Basket" :
                for (SportCenterEntity sportCenter : sportCenterList){
                    for (SportCourt court : sportCenter.getCourts().getBasketCourts()){
                        timeSlotList.addAll(court.getBookingTable());
                    }
                }
                break;
            case "Padel":
                for (SportCenterEntity sportCenter : sportCenterList){
                    for (SportCourt court : sportCenter.getCourts().getPadelCourts()){
                        timeSlotList.addAll(court.getBookingTable());
                    }
                }
                break;
            case "Tennis":
                for (SportCenterEntity sportCenter : sportCenterList){
                    for (SportCourt court : sportCenter.getCourts().getTennisCourts()){
                        timeSlotList.addAll(court.getBookingTable());
                    }
                }
                break;
            default:
        }

        //Compares its two arguments for order. Returns a negative integer, zero, or a positive integer
        // as the first argument is less than, equal to, or greater than the second.
        Comparator<TimeSlot> timeSlotComparator = Comparator.comparingInt(TimeSlot::getAvailableSpots);

        timeSlotList.sort(timeSlotComparator);

        int lenght = (Math.min(timeSlotList.size(), MAX_RESULTSET_DIMENSION));
        return timeSlotList.subList(0, lenght) ;


    }

}
