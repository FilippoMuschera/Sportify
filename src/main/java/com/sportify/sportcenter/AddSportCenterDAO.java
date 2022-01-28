package com.sportify.sportcenter;

import com.sportify.sportcenter.courts.SportCourt;
import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.sportcenter.exceptions.SportCenterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sportify.utilitydb.DBConnector.getConnector;

public class AddSportCenterDAO {

    private boolean error = false;

    public void addSCToDB(SportCenterEntity sportCenter) throws SportCenterException {

        this.addSportCenter(sportCenter);
        this.addSportCenterPosition(sportCenter);
        this.addCourts(sportCenter);

        if (this.error) {
            throw new SportCenterException("errore nell'aggiunta dello sport center al database");
        }
    }


    private void addCourts(SportCenterEntity sportCenter) {

        List<List<SportCourt>> sportsList = new ArrayList<>();
        sportsList.add(sportCenter.getCourts().getFootballFields());
        sportsList.add(sportCenter.getCourts().getPadelCourts());
        sportsList.add(sportCenter.getCourts().getBasketCourts());
        sportsList.add(sportCenter.getCourts().getTennisCourts());

        String queryCourt = "INSERT INTO `sportify_db`.`SportCourt` (`id`, `NameSC`, `Sport`) VALUES (?, ?, ?);";
        String queryTimeSlot = "INSERT INTO `sportify_db`.`TimeSlot` (`idCampo`, `NameSC`, `startH`, `finishH`, `availableSpots`, `Sport`) " +
                "VALUES (?, ?, ?, ?, ? , ?);";

        try (Connection con = getConnector()) {

            try (PreparedStatement preparedStatement = con.prepareStatement(queryCourt);
                 PreparedStatement psTimeSlot = con.prepareStatement(queryTimeSlot)) {
                preparedStatement.setString(2, sportCenter.getInfo().getSportCenterName());
                psTimeSlot.setString(2, sportCenter.getInfo().getSportCenterName());
                for (List<SportCourt> courts : sportsList) { //elenco dei tipi di campo
                    for (SportCourt court : courts) { //per ogni campo di uno sport
                        preparedStatement.setInt(1, court.getCourtID());
                        preparedStatement.setString(3, court.getSport());
                        preparedStatement.executeUpdate();
                        for (TimeSlot ts : court.getBookingTable()) { //per ogni time slot di un campo
                            psTimeSlot.setInt(1, court.getCourtID());
                            psTimeSlot.setInt(3, ts.getStartTime().getHour());
                            psTimeSlot.setInt(4, ts.getEndTime().getHour());
                            psTimeSlot.setInt(5, ts.getAvailableSpots());
                            psTimeSlot.setString(6, court.getSport());
                            psTimeSlot.executeUpdate();
                        }


                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            this.error = true;
        }
    }


    private void addSportCenterPosition(SportCenterEntity sportCenter) {
        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "INSERT INTO `sportify_db`.`SportCenterPosition` (`NameSC`, `Lat`, `Lng`) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, sportCenter.getInfo().getSportCenterName());
                preparedStatement.setDouble(2, sportCenter.getLat());
                preparedStatement.setDouble(3, sportCenter.getLong());

                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.error = true;
        }
    }


    private void addSportCenter(SportCenterEntity sportCenter) {
        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "INSERT INTO `sportify_db`.`SportCenter` (`Name`, `Address`, `OwnerEmail`, `OpeningH`, `ClosingH`) " +
                    "VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, sportCenter.getInfo().getSportCenterName());
                preparedStatement.setString(2, sportCenter.getInfo().getSportCenterAddress());
                preparedStatement.setString(3, sportCenter.getInfo().getOwnerEmail());
                preparedStatement.setInt(4, sportCenter.getTimetable().getOpeningTime().getHour());
                preparedStatement.setInt(5, sportCenter.getTimetable().getClosingTime().getHour());

                preparedStatement.executeUpdate();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            this.error = true;
        }
    }

    public void updateTimeSlot(int availableSpots, int id, String sport, String nameSC, int startH, int finishH) {
        try (Connection con = getConnector()) {
            if (con == null)
                throw new SQLException();
            String query = "UPDATE `sportify_db`.`TimeSlot`SET`availableSpots` = ? " +
                    "WHERE `idCampo` = ? AND `Sport` = ? AND `NameSC` = ? AND `startH` = ? AND `finishH` = ?;";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, availableSpots);
                ps.setInt(2, id);
                ps.setString(3, sport);
                ps.setString(4, nameSC);
                ps.setInt(5, startH);
                ps.setInt(6, finishH);

                ps.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
