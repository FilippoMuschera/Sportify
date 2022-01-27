package com.sportify.joinmatch;

public class JoinMatchBean {

    private String selectedSport;
    private boolean distanceIsImportant = false;
    private boolean availableSpotIsImportant = false;
    private String preferredStartingTimeString;
    private String maxResultsString;
    private int preferredStartingTime;
    private int maxResults;

    private boolean isValid = false;




    public void validateInput() throws IllegalArgumentException {
        if (this.preferredStartingTimeString.isEmpty() || this.maxResultsString.isEmpty())
            throw new IllegalArgumentException("One or more fields are empty");
        try {
            this.maxResults = Integer.parseInt(maxResultsString);
            this.preferredStartingTime = Integer.parseInt(preferredStartingTimeString);
        } catch (NumberFormatException e){
            //Se una delle due stringhe non rappresenta un intero allora uno degli argomenti passati alla bean è
            //sintatticamente scorretto, e lanciamo un'eccezione così che la view possa notificarlo all'utente
            throw new IllegalArgumentException("Starting Time and Results Number must be a number!");
        }
        if (this.preferredStartingTime < 0 || this.preferredStartingTime > 23)
            throw new IllegalArgumentException("Starting time can't be negative or grater than 23!");
        if (this.maxResults <= 0)
            throw new IllegalArgumentException("Results number must be grater than zero!");
        this.isValid = true;




    }

    public ResultSetEntity executeJoinMatch() {
        //Se tutti i controlli sono superati si lancia l'esecuzione dello use case invocando il controller
        if (this.isValid) {
            JoinMatchController controller = new JoinMatchController();
            return controller.findJoinableMatch(this);
        }
        else {
            throw new IllegalCallerException("Non si può eseguire lo use case senza aver validato prima l'input sulla bean");
        }
    }




    //Getter and Setters

    public void setSelectedSport(String selectedSport) {
        this.selectedSport = selectedSport;
    }

    public void setDistanceIsImportant(boolean distanceIsImportant) {
        this.distanceIsImportant = distanceIsImportant;
    }

    public void setAvailableSpotIsImportant(boolean availableSpotIsImportant) {
        this.availableSpotIsImportant = availableSpotIsImportant;
    }

    public void setPreferredStartingTimeString(String preferredStartingTimeString) {
        this.preferredStartingTimeString = preferredStartingTimeString;
    }

    public void setMaxResultsString(String maxResultsString) {
        this.maxResultsString = maxResultsString;
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
