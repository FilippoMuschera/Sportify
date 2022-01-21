package com.sportify.sportcenter;

public class SportCenterInfo {
    private final String ownerEmail;
    private final String sportCenterName;
    private final String sportCenterAddress;
    private boolean notifications;


    public SportCenterInfo(String ownerEmail, String sportCenterName, String sportCenterAddress, boolean notifications) {
        this.ownerEmail = ownerEmail;
        this.sportCenterName = sportCenterName;
        this.sportCenterAddress = sportCenterAddress;
        this.notifications = notifications;
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

    public boolean isNotifications() {
        return notifications;
    }
}
