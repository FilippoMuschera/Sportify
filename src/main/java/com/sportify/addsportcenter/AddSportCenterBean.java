package com.sportify.addsportcenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSportCenterBean {

    private String sportCenterName;
    private String sportCenterAddress;
    private int numOfFootballFields;
    private int numOfPadelCourts;
    private int numOfBasketCourts;
    private int numOfTennisCourts;
    private int openingHour;
    private int closingHour;


    //Setters

    public void setSportCenterName(String sportCenterName) {
        if (sportCenterName.isEmpty())
            throw new IllegalArgumentException("Sport Center Name cannot be empty!");
        this.sportCenterName = sportCenterName;
    }

    public void setSportCenterAddress(String sportCenterAddress) {
        if (sportCenterAddress.isEmpty())
            throw new IllegalArgumentException("sport Center Address cannot be empty!");
        if (sportCenterAddress.split(",").length != 3)
            throw new IllegalArgumentException("Address is malformed. Please use the format: Street HouseNumber, City, ZIP Code");
        Pattern validCAP = Pattern.compile("^[0-9]{5}$"); //valida sintatticamente il CAP
        //Analizza il 3o pezzo dell'address (è già stato controllato che ce ne siano esattamente tre), rimuove gli spazi
        //in eccesso con "trim()" e lo confronta con la regex
        Matcher m = validCAP.matcher(sportCenterAddress.split(",")[2].trim());
        if(!m.find())
            throw new IllegalArgumentException("Invalid CAP!");

        this.sportCenterAddress = sportCenterAddress;
    }

    public void setNumOfFootballFields(int numOfFootballFields) {
        if (numOfFootballFields < 0)
            throw new IllegalArgumentException("Number of Football Fields is negative");
        this.numOfFootballFields = numOfFootballFields;
    }

    public void setNumOfPadelCourts(int numOfPadelCourts) {
        if (numOfPadelCourts < 0)
            throw new IllegalArgumentException("Number of Padel Courts is negative");
        this.numOfPadelCourts = numOfPadelCourts;
    }

    public void setNumOfBasketCourts(int numOfBasketCourts) {
        if (numOfBasketCourts < 0)
            throw new IllegalArgumentException("Number of Basket Courts is negative");
        this.numOfBasketCourts = numOfBasketCourts;
    }

    public void setNumOfTennisCourts(int numOfTennisCourts) {
        if (numOfTennisCourts < 0)
            throw new IllegalArgumentException("Number of Tennis Courts is negative");
        this.numOfTennisCourts = numOfTennisCourts;
    }

    public void setOpeningHour(int openingHour) {
        if (openingHour < 0 || openingHour > 23)
            throw new IllegalArgumentException("Opening Hour must be between 00 and 23");
        this.openingHour = openingHour;

    }


    public void setClosingHour(int closingHour) {
        if (closingHour < 0 || closingHour > 23)
            throw new IllegalArgumentException("Closing Hour must be between 00 and 23");
        this.closingHour = closingHour;
    }

    //getters


    public String getSportCenterName() {
        return sportCenterName;
    }

    public String getSportCenterAddress() {
        return sportCenterAddress;
    }

    public int getNumOfFootballFields() {
        return numOfFootballFields;
    }

    public int getNumOfPadelCourts() {
        return numOfPadelCourts;
    }

    public int getNumOfBasketCourts() {
        return numOfBasketCourts;
    }

    public int getNumOfTennisCourts() {
        return numOfTennisCourts;
    }

    public int getOpeningHour() {
        return openingHour;
    }


    public int getClosingHour() {
        return closingHour;
    }


}
