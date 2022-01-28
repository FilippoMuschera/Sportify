package com.sportify.joinmatch;

import com.sportify.email.EmailThread;
import com.sportify.geolocation.Geolocator;
import com.sportify.sportcenter.AddSportCenterDAO;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.SportCenterInfo;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class JoinMatchController {

    private ResultSetEntity resultSet = new ResultSetEntity();
    private boolean isDistanceImportant;
    private boolean isAvailableSpotsImportant;


    public void findJoinableMatch(JoinMatchBean bean) {
        int maxSportCenter = 20;

        //Settiamo gli attributi per ponderare il peso di distanza e posti disponibili
        this.isDistanceImportant = bean.isDistanceIsImportant();
        this.isAvailableSpotsImportant = bean.isAvailableSpotIsImportant();

        //Per prima cosa bisogna trovare gli sport Center nel raggio dell'utente
        GetSportCenterDAO getSportCenterDAO = GetSportCenterDAO.getInstance();
        Geolocator g = Geolocator.getInstance();
        UserPreferences preferences = UserEntity.getInstance().getPreferences();

        //Uso la getNearSportCenter per trovare (al più) bean.getMaxResults() campi sportivi nel raggio selezionato dall'utente
        Map<String, Double> sportCenterMap = getSportCenterDAO.getNearSportCenters(bean.getSelectedSport(), maxSportCenter,
                g.getLat(preferences.getUserAddress()), g.getLng(preferences.getUserAddress()));

        //TODO ECCEZIONE NON TROVO CENTRI SPORTIVI

        //Per ogni sport center che abbiamo trovato andiamo a caricare i campi relativi allo sport scelto dall'utente
        for (Map.Entry<String, Double> entry : sportCenterMap.entrySet()){
            SportCenterEntity currentSportCenter = getSportCenterDAO.getSportCenter(entry.getKey(), bean.getSelectedSport()); //carichiamo la SportCenterEntity dal DB
            for (SportCourt court : this.getSelectedSportCourts(currentSportCenter, bean.getSelectedSport())){

                //Per ogni campo sportivo scorriamo i time slot
                for (TimeSlot t : court.getBookingTable()){

                    //Il primo TimeSlot che inizia dopo l'orario scelto (o esattamente all'orario scelto) viene selezionato
                    if ((t.getStartTime().isAfter(LocalTime.of(bean.getPreferredStartingTime(), 0)) ||
                            t.getStartTime().getHour() == bean.getPreferredStartingTime()) &&
                    t.getAvailableSpots() < court.getMaxSpots()){

                        //Si costruisce il ResultElement
                        ResultElement resultElement = new ResultElement();
                        resultElement.setCourtID(court.getCourtID());
                        resultElement.setNameSC(currentSportCenter.getInfo().getSportCenterName());
                        resultElement.setTimeSlot(t);
                        resultElement.setDistance(entry.getValue());
                        resultElement.setMaxSpots(court.getMaxSpots());
                        resultElement.setSport(bean.getSelectedSport());

                        //Si aggiunge il ResultElement al ResultSet
                        this.resultSet.addElement(resultElement);
                        break; //si esce dal ciclo for sui TimeSlot perché abbiamo preso quello che ci interessava
                    }
                }
            }
        }
        //A questo punto il nostro ResultSetEntity aggrega tutti i ResultElement che ci servono, ora bisogna ordinarli
        //in base al loro indexValue
        //TODO CASO CON 0 RISULTATI
        this.evaluateIndexValues();
        int min = Math.min(this.resultSet.getElements().size(), bean.getMaxResults());
        System.out.println(bean.getMaxResults());
        ArrayList<ResultElement> shrunkList = new ArrayList<>();

        for (int i = 0; i< min; i++){
            shrunkList.add(this.resultSet.getElements().get(i));
        }

        resultSet.setElementsList(shrunkList);
        bean.setResultSet(this.resultSet);


    }

    private void evaluateIndexValues() {

        //Setta l'indexValue di ogni ResultElement
        for (ResultElement element : resultSet.getElements())
            element.setIndexValue(this.calculateIndexValue(element));



        Comparator<ResultElement> indexValueComparator = Comparator.comparingDouble(ResultElement::getIndexValue);
        // Il comparatore compara gli index value dei ResultElement


        this.resultSet.getElements().sort(indexValueComparator); //ordiniamo gli elementi in ordine crescente d'indexValue


    }

    private double calculateIndexValue(ResultElement element) {

        /* Qui ci va un bel commento che spiega cos'è e come si calcola l'indexValue */

        double selectedDistance = UserEntity.getInstance().getPreferences().getSortingDistance();
        double maxAvailableSpots = element.getMaxSpots();
        double highWeight = 5;
        double lowWeight = 1;
        double pondDist = isDistanceImportant ? highWeight : lowWeight;
        double pondAvailableSpots = isAvailableSpotsImportant ? highWeight : lowWeight;

        double distValue = (element.getDistance()/selectedDistance)*pondDist;
        double availableSpotsValue = (element.getTimeSlot().getAvailableSpots()/maxAvailableSpots)*pondAvailableSpots;
        return (distValue + availableSpotsValue);
    }

    private List<SportCourt> getSelectedSportCourts(SportCenterEntity currentSportCenter, String selectedSport) {
        return switch (selectedSport) {
            case "Football" -> currentSportCenter.getCourts().getFootballFields();
            case "Padel" -> currentSportCenter.getCourts().getPadelCourts();
            case "Tennis" -> currentSportCenter.getCourts().getTennisCourts();
            case "Basket" -> currentSportCenter.getCourts().getBasketCourts();
            default -> null;
        };
    }

    public void joinMatch(ResultElement selectedMatch){

        AddSportCenterDAO newAddSportCenterDAO = new AddSportCenterDAO();
        int spots = selectedMatch.getTimeSlot().getAvailableSpots();
        int selectedCourtID = selectedMatch.getCourtID();
        String selectedSport = selectedMatch.getSport();
        String selectedSportCenter = selectedMatch.getNameSC();
        int startTime = selectedMatch.getTimeSlot().getStartTime().getHour();
        int finishTime = selectedMatch.getTimeSlot().getEndTime().getHour();

        newAddSportCenterDAO.updateTimeSlot(spots-1, selectedCourtID, selectedSport,
                selectedSportCenter, startTime, finishTime);

    }


    public void sendEmails(ResultElement selectedMatch) {

        UserPreferences preferences = UserEntity.getInstance().getPreferences();
        SportCenterInfo infoSportCenter = GetSportCenterDAO.getInstance().getSportCenter(selectedMatch.getNameSC(),
                selectedMatch.getSport()).getInfo();
        int startTime = selectedMatch.getTimeSlot().getStartTime().getHour();
        int finishTime = selectedMatch.getTimeSlot().getEndTime().getHour();

        //Se l'utente che sta usando l'app ha le notifiche attive si invia la mail promemoria del match
        if (preferences.isNotifications()){



            EmailThread playerEmailThread = new EmailThread(selectedMatch.getSport(), selectedMatch.getCourtID(),
                    startTime, finishTime, infoSportCenter.getSportCenterAddress());
            playerEmailThread.setPlayer(true);
            playerEmailThread.start();
        }

        //Se l'owner del campo ha le notifiche attive, e il match è al completo => inviamo la mail all'owner per avvisarlo
        if (infoSportCenter.isNotifications() && selectedMatch.getTimeSlot().getAvailableSpots() == 0){
            EmailThread ownerEmailThread = new EmailThread(infoSportCenter.getOwnerEmail(),
                    selectedMatch.getSport(), selectedMatch.getCourtID(), startTime, finishTime);
            ownerEmailThread.setOwner(true);
            ownerEmailThread.start();
        }



    }


}
