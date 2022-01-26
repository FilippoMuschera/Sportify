package com.sportify.email;

public class EmailThread extends Thread{

    private String ownerEmail;
    private final String sport;
    private final int courtID;
    private final int startTime;
    private final int finishTime;
    private String sportCenterAddress;
    private boolean isOwner = false;
    private boolean isPlayer = false;

    @Override
    public void run(){
        if (isOwner)
            new EmailSender().sendOwnerEmail(this.ownerEmail, this.sport, this.courtID, this.startTime, this.finishTime);
        else if (isPlayer)
            new EmailSender().sendPlayerEmail(this.sport, this.courtID, this.sportCenterAddress, this.startTime, this.finishTime);
    }

    public EmailThread(String ownerEmail, String sport, int courtID, int startTime, int finishTime) {
        this.ownerEmail = ownerEmail;
        this.sport = sport;
        this.courtID = courtID;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public EmailThread(String sport, int courtID, int startTime, int finishTime, String sportCenterAddress) {
        this.sport = sport;
        this.courtID = courtID;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.sportCenterAddress = sportCenterAddress;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
