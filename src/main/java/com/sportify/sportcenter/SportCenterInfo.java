package com.sportify.sportcenter;

public class SportCenterInfo {
    private final String ownerEmail;
    private final String sportCenterName;
    private final String sportCenterAddress;


    public SportCenterInfo(String ownerEmail, String sportCenterName, String sportCenterAddress) {
        this.ownerEmail = ownerEmail;
        this.sportCenterName = sportCenterName;
        this.sportCenterAddress = sportCenterAddress;
    }


    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public String getSportCenterAddress() {
        return sportCenterAddress;
    }


}
