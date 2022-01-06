package com.sportify.sportcenter;

public class SportCenterInfo {
    private String ownerEmail;
    private String ownerFirstName;
    private String ownerLastName;
    private String sportCenterName;
    private String sportCenterAddress;


    public SportCenterInfo(String ownerEmail, String ownerFirstName, String ownerLastName, String sportCenterName, String sportCenterAddress) {
        this.ownerEmail = ownerEmail;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.sportCenterName = sportCenterName;
        this.sportCenterAddress = sportCenterAddress;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public String getSportCenterAddress() {
        return sportCenterAddress;
    }


}
