package com.sportify.joinmatch;


import java.util.Arrays;

public class JoinMatchBean {

    private String selectedSport;
    private boolean distanceIsImportant = false;
    private boolean availableSpotIsImportant = false;
    private int preferredStartingTime;
    private int maxResults;
    private ResultSetEntity resultSet;


   //Setters

    public void setSelectedSport(String selectedSport) throws IllegalArgumentException {
        String[] sports = {"Football", "Padel", "Tennis", "Basket"};
        if (!Arrays.asList(sports).contains(selectedSport) || selectedSport.isEmpty())
            throw new IllegalArgumentException("Invalid Sport!");
        this.selectedSport = selectedSport;
    }

    public void setDistanceIsImportant(boolean distanceIsImportant) {
        this.distanceIsImportant = distanceIsImportant;
    }

    public void setAvailableSpotIsImportant(boolean availableSpotIsImportant) {
        this.availableSpotIsImportant = availableSpotIsImportant;
    }

    public void setPreferredStartingTime(String preferredStartingTimeString) throws IllegalArgumentException {
        if (preferredStartingTimeString.isEmpty())
            throw new IllegalArgumentException("Starting time cannot be empty!");
        int preferredTime;
        try {
            preferredStartingTimeString = preferredStartingTimeString.trim();
            preferredTime = Integer.parseInt(preferredStartingTimeString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Starting time must be a number!");
        }
        if (preferredTime > 23 || preferredTime < 0)
            throw new IllegalArgumentException("Starting time must be between 00 and 23");
        this.preferredStartingTime = preferredTime;

    }

    public void setMaxResults(String maxResultsString) {
        if (maxResultsString.isEmpty())
            throw new IllegalArgumentException("Max results number cannot be empty!");
        int max;
        try {
            max = Integer.parseInt(maxResultsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Max results number has a wrong format");
        }
        if (max <= 0)
            throw new IllegalArgumentException("Results number must be greater than zero!");
        this.maxResults = max;
    }

    public void setResultSet(ResultSetEntity resultSet) {
        this.resultSet = resultSet;
    }


    //getters

    public ResultSetEntity getResultSet() {
        return resultSet;
    }

    public String getSelectedSport() {
        return selectedSport;
    }

    public boolean isDistanceIsImportant() {
        return distanceIsImportant;
    }

    public boolean isAvailableSpotIsImportant() {
        return availableSpotIsImportant;
    }

    public int getPreferredStartingTime() {
        return preferredStartingTime;
    }

    public int getMaxResults() {
        return maxResults;
    }
}
