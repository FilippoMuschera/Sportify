package com.sportify.joinmatch;

import com.sportify.geolocation.Geolocator;
import com.sportify.sportcenter.GetSportCenterDAO;
import com.sportify.sportcenter.SportCenterEntity;
import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.user.UserEntity;

import java.util.*;

public class JoinMatchControllerVecchio {

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

/* PREMESSA: in una situazione normale, anche in un raggio di 10km o 15km, i centri sportivi sono pochi, massimo 4 o 5,
ma non conosco posto che ne abbia così tanti, e cmq anche i singoli centri sportivi non hanno piu di 2 o 3 campi dello stesso sport.
 Questo per dirti che la soluzione che propongo è lunga però questa è la situazione a cui si applica.

FASE 0: l utente indica cosa per lui ha piu peso, distanza o numero persone, e lo sport. Volendo anche il numero di partite che vuole ottenere.

INIZIO: si recuperano tutti i centri sportivi all interno del raggio scelto dall utente. Se non abbiamo questa query, penso che basta quella di
 nearSportCenter ma modificare la query sql. Inoltre si possono alleggerire tutte le classi che gia esistono levando la robba che istanzia informazioni
 al momento non utili alla join (in caso si creano copie leggere di queste classi, semza impazzire a modificare quelle già esistenti).
  Quindi a questo punto avremo un array o lista di sportcenter.

FASE 2: si itera sulla lista di SC (sport center, sc per abbreviare). Per ogni sport center andiamo a prendere tutti i
 campi dello sport selezionato e andiamo a inserirli in una classe di supporto, per il momento chiamiamola SashaClass.
 La SashaClass avrà un parametro Stringa che è il nome dello SC, una map con chiave l id dei campi e valore il ValoreIndice
 risultato di una delle nostre equazioni, dopo ti dico la mia. Inoltre in questa classe mettiamo tutte le info che possono essere
 utili all utente, tipo SlotTime e altro. In questa fase la map avrà come valori tutti 0. Inoltre avremo una SashaClass per ogni SC.

FASE 3: in ogni SashaClass andiamo a iterare sulla lista dei campi, (volendo possiamo creare una lista di supporto che tiene traccia dei campi)
e andiamo a premdere per ogni campo un solo hour slot che sia quello con meno posti disponibili, cioè quello più "quasi pieno". Anche qui si può usare
una map con chiave id del campo e valore il TimeSlot selezionato. Quando abbiamo preso il Timeslot che ha meno posti disponibili calcoliamo anche il
 ValoreIndice e lo inseriamo nella map della SashaClass. Al termine di questa fase dovremmo avere le varie SashaClass per SC con le loro map piene.

FASE 4: andiamo a selezionare le nostre partite con ValoreIndice migliore e le andiamo a inserire in una ClasseRisultato che sarà quella
che il controller applicativo passa al controller view. Il numero di partite possono anche essere inserite dall utente e quindi restituire piu
 o meno partite. Il modo in cui inseriamo i vari pezzi di varie Sashaclass in ClasseRsultato ancora non l ho pensato, ma questa cclasse risultato prende
  le info che hanno le SashaClass e le riporta dentro se, così che la view possa anche indicare all utente le info principali delle partite trovate.

[20:18, 20/1/2022] Gianluca Maccari: Allora:
Chiamo disCampo la distanza del singolo campo dall utente (nom so se gia ce l abbiamo)
Chiamo disSel la distanza che ha l utente nelle preferences
Chiamo numDisp il numero di giocatori che mancano
Chiamo numTot il numero massimo di giocatori
Chiamo pondX e pondY i valori pesati che sono diversi o uguali tra loro in base a come vuole l utente
La formula è questa:
(disCampo/disSel)×pondX + (numDisp/numTot)×pondY
Questo è il ValoreIndice
[20:18, 20/1/2022] Gianluca Maccari: Le migliori partite sono quelle con valoreIndice piu piccolo
[20:20, 20/1/2022] Gianluca Maccari: Cosi sia la distanza che i giocatori sono valori minori di 1 e maggiori di 0, e il peso maggiore glielo do con pondx e y.
 */

