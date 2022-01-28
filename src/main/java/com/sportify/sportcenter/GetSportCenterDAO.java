package com.sportify.sportcenter;

import com.sportify.sportcenter.courts.*;
import com.sportify.sportcenter.exceptions.SportCenterException;
import com.sportify.user.UserEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

import static com.sportify.utilitydb.DBConnector.getConnector;

public class GetSportCenterDAO {

    private static GetSportCenterDAO singletonInstance = null;

    private final UserEntity user = UserEntity.getInstance();



    public Map<String, Double> getNearSportCenters(String selectedSport, int maxNumberOfResults, double userLat, double userLng) throws NullPointerException, SportCenterException {
        try {
            Connection con = getConnector();
            if (con == null)
                throw new SQLException();
            String query = """
                    SELECT S.NameSC, 2*6371 * asin(sqrt(0.5 - cos((Lat-?) * 0.0174532925199432)/2 +
                                    cos(? * 0.0174532925199432) * cos(Lat * 0.0174532925199432) *
                                            (1 - cos((Lng - ?) * 0.0174532925199432))/2)) AS distance
                    FROM `sportify_db`.`SportCenterPosition` S
                    HAVING distance < ? AND exists(SELECT T.NameSC FROM `sportify_db`.`TimeSlot` as T where T.NameSC = S.NameSC and availableSpots > 0 and T.Sport = ?)
                    ORDER BY distance
                    LIMIT 0 , ?;""";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setDouble(1, userLat);
                ps.setDouble(2, userLat);
                ps.setDouble(3, userLng);
                ps.setInt(4, user.getPreferences().getSortingDistance());
                ps.setString(5, selectedSport);
                ps.setInt(6, maxNumberOfResults);

                ResultSet rs = ps.executeQuery();
                if (!rs.next())
                    throw new SportCenterException("The query didn't provide a result in finding nearest sport centers");
                Map<String, Double> sportCentersMatrix = new HashMap<>();

                do {
                    sportCentersMatrix.put(rs.getString("NameSC"), rs.getDouble("distance"));
                } while (rs.next());
                return sportCentersMatrix;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Collections.emptyMap();
    }


    public SportCenterEntity getSportCenter(String scName, String selectedSport) {
        try {
            Connection con = getConnector();
            if (con == null)
                throw new SQLException();

            //Crea le istanze delle classi che compongono lo sport center
            SportCenterEntity sportCenter = new SportCenterEntity();
            SportCenterInfo info;
            SportCenterTime time;
            SportCenterCourts courts = new SportCenterCourts();

            //Richiede al DB Le info sullo sport center e sugli orari di apertura/chiusura
            String querySc = """
                    SELECT Name, ownerEmail, openingH, closingH, SportCenter.Address, Notifications
                     FROM sportify_db.SportCenter join sportify_db.UsersPreferences on Email = ownerEmail
                     WHERE Name = ?;
                    """;
            try (PreparedStatement ps = con.prepareStatement(querySc)){
                ps.setString(1, scName);
                ResultSet rs = ps.executeQuery();
                if (!rs.next())
                    throw new NullPointerException("Sport Center Not Found");
                info = new SportCenterInfo(rs.getString("ownerEmail"), rs.getString("Name"),
                        rs.getString("Address"), rs.getBoolean("Notifications"));
                time = new SportCenterTime(rs.getInt("openingH"), rs.getInt("closingH"));

                //assegna le info recuperate dal db allo sport center
                sportCenter.setInfo(info);
                sportCenter.setTimetable(time);
            }
            //Richiede al DB le coordinate dello SC
            String queryCoord = "SELECT * FROM sportify_db.SportCenterPosition WHERE NameSC = ?;";
            try (PreparedStatement ps = con.prepareStatement(queryCoord)){
                ps.setString(1, scName);
                ResultSet rs = ps.executeQuery();
                if (!rs.next())
                    throw new NullPointerException("Sport Center Not Found");

                //assegna le coordinate allo sport center
                sportCenter.setCoordinates(rs.getDouble("Lat"), rs.getDouble("Lng"));


            }

            //richiede al DB i campi sportivi per lo sport richiesto
            String queryCourts = "SELECT * FROM sportify_db.SportCourt WHERE NameSC = ? and Sport = ? ;";
            try (PreparedStatement ps = con.prepareStatement(queryCourts)) {
                ps.setString(1, scName);
                ps.setString(2, selectedSport);
                ResultSet rs = ps.executeQuery(); //questo result set contiene tutti i campi dello sport center per lo sport scelto
                if (!rs.next())
                    throw new NullPointerException("Sport Court(s) Not Found");
                do {
                    //richiede aò DB gli hour slot per ogni campo trovato
                    String queryTimeSlot = "Select * FROM sportify_db.TimeSlot WHERE " +
                            "NameSC = ? and idCampo = ? and Sport = ?;";
                    List<TimeSlot> timeSlotList = new ArrayList<>();
                    try (PreparedStatement psTimeSlot = con.prepareStatement(queryTimeSlot)){
                        psTimeSlot.setString(1, rs.getString("NameSC"));
                        psTimeSlot.setInt(2, rs.getInt("id"));
                        psTimeSlot.setString(3, selectedSport);
                        ResultSet rsTimeSlot = psTimeSlot.executeQuery(); // questo rs contiene tutti gli hour slot di un campo
                        if (!rsTimeSlot.next())
                            throw new NullPointerException("TimeSlot(s) Not Found");
                        do {
                             timeSlotList.add(new TimeSlot(LocalTime.of(rsTimeSlot.getInt("startH"), 0),
                                    LocalTime.of(rsTimeSlot.getInt("finishH"), 0), rsTimeSlot.getInt("availableSpots")));
                        } while (rsTimeSlot.next());

                        //A questo punto la DAO ha recuperato tutti gli hour slot del primo campo, e procede quindi a istanziarlo
                        SportCourt courtToAdd = switch (selectedSport) {
                            case "Football" -> new FootballField(timeSlotList, rs.getInt("id"));
                            case "Padel" -> new PadelCourt(timeSlotList, rs.getInt("id"));
                            case "Basket" -> new BasketCourt(timeSlotList, rs.getInt("id"));
                            case "Tennis" -> new TennisCourt(timeSlotList, rs.getInt("id"));
                            default -> null;
                        };
                        courts.addField(selectedSport, courtToAdd);
                    }

                } while (rs.next());

                //A questo punto la DAO ha tutti i campi del centro sportivo per lo sport scelto
                sportCenter.setCourts(courts);
                return sportCenter;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    private GetSportCenterDAO(){}

    public static GetSportCenterDAO getInstance(){
        if (GetSportCenterDAO.singletonInstance == null)
            GetSportCenterDAO.singletonInstance = new GetSportCenterDAO();
        return GetSportCenterDAO.singletonInstance;
    }
}
