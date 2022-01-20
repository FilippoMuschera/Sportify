package com.sportify.sportcenter;

public class SportCenterEntity {

    private SportCenterInfo info;
    private SportCenterCourts courts;
    private SportCenterTime timetable;
    private double[] coordinates = new double[2];


    public void setCoordinates(double lat, double lng) {
        double[] coord = new double[2];
        coord[0] = lat;
        coord[1] = lng;
        this.coordinates = coord;
    }

    public double getLat() {
        return this.coordinates[0];
    }

    public double getLong() {
        return this.coordinates[1];
    }

    public void setInfo(SportCenterInfo info) {
        this.info = info;
    }

    public void setCourts(SportCenterCourts courts) {
        this.courts = courts;
    }

    public void setTimetable(SportCenterTime timetable) {
        this.timetable = timetable;
    }

    public SportCenterTime getTimetable() {
        return timetable;
    }

    public SportCenterInfo getInfo() {
        return info;
    }

    public SportCenterCourts getCourts() {
        return courts;
    }
}
