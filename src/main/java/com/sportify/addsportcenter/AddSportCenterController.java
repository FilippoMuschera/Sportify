package com.sportify.addsportcenter;

import com.sportify.geolocation.Geolocator;
import com.sportify.sportcenter.*;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class AddSportCenterController {
    public void addSportCenter(AddSportCenterBean bean) {


        //Calcolo coordinate del campo dall'indirizzo
        double[] coord = Geolocator.getCoordinates(bean.getSportCenterAddress());
        SportCenterEntity sportCenter = new SportCenterEntity();
        sportCenter.setCoordinates(coord);

        //Aggiugno le info al campo sportivo
        UserEntity user = UIController.getUIControllerInstance().getUser();
        SportCenterInfo info = new SportCenterInfo(user.getEmail(), bean.getFirstName(), bean.getLastName(),
                bean.getSportCenterName(), bean.getSportCenterAddress());
        sportCenter.setInfo(info);

        //Aggiungo gli orari al campo sportivo

        SportCenterTime times = new SportCenterTime(bean.getOpeningHour(), bean.getClosingHour());
        sportCenter.setTimetable(times);

        List<TimeSlot> timeSlots = this.getTimeSlots(sportCenter);

        //Aggiungo i campi disponibili al campo sportivo
        SportCenterCourts courts = new SportCenterCourts(bean.getNumOfFootballFields(),bean.getNumOfPadelCourts(),
                bean.getNumOfBasketCourts(), bean.getNumOfTennisCourts(), timeSlots);
        sportCenter.setCourts(courts);



        AddSportCenterDAO dao = new AddSportCenterDAO();
        dao.addSCToDB(sportCenter);

        //TODO gestione delle eccezioni che la classe dao può generare (es.: centro sportivo già presente ecc...)
    }

    private List<TimeSlot> getTimeSlots(SportCenterEntity sc) {
        LocalTime opening = sc.getTimetable().getOpeningTime();
        LocalTime closing = sc.getTimetable().getClosingTime();
        LocalTime actualTime = opening;
        List<TimeSlot> timeSlots = new ArrayList<>();
        while (actualTime.plusHours(TimeSlot.DEFAULT_SLOT_DURATION).isBefore(closing)){ //c'è uno slot di DEFAULT_TIME disponibile
            TimeSlot n = new TimeSlot(actualTime, actualTime.plusHours(TimeSlot.DEFAULT_SLOT_DURATION));
            timeSlots.add(n);
            actualTime = actualTime.plusHours(TimeSlot.DEFAULT_SLOT_DURATION);
        }
        if (actualTime.isBefore(closing)){ //Se il DEFAULT_SLOT_DURATION viene impostato a 1h questa porzione di codice
                                           //non verrà mai eseguita. Non è comunque un caso che si prevede di usare.
            TimeSlot last = new TimeSlot(actualTime, closing);
            timeSlots.add(last);
        }

        return timeSlots;


    }
}
