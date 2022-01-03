package com.sportify.addsportcenter;

public class AddSportCenterBean {

    private String firstName;
    private String lastName;
    private String sportCenterName;
    private String sportCenterAddress;
    private int numOfFootballFields;
    private int numOfPadelCourts;
    private int numOfBasketCourts;
    private int numOfTennisCourts;
    private int openingHour;
    private int openingMinutes;
    private int closingHour;
    private int closingMinutes;


    public void addSportCenter(){
        this.validateInput();
        AddSportCenterController controller = new AddSportCenterController();
        controller.addSportCenter(this);
    }

    private void validateInput(){
        if (this.firstName.isEmpty() || this.lastName.isEmpty() || this.sportCenterAddress.isEmpty()
        || this.sportCenterName.isEmpty()) {
            throw new IllegalArgumentException("There is an empty field!");
        }
        if (this.validFields(numOfFootballFields, numOfTennisCourts, numOfPadelCourts, numOfBasketCourts)){
            throw new IllegalArgumentException("Number of fields/courts can't be lower than 0!");
        }
        if (!this.validTime(openingHour, openingMinutes, closingHour, closingMinutes)){
            throw new IllegalArgumentException("Invalid time field");
        }
    }

    private boolean validFields(int... fields){
        for (int f : fields){
            if (f < 0)
                return false;
        }
        return true;
    }

    private boolean validTime(int openH, int openM, int closingH, int closingM){
        if (openH < 0 || openH > 23 || closingH < 0 || closingH > 23)
            return false;
        if (openM < 0 || openM > 59 || closingM < 0 || closingM > 59)
            return false;
        if (openH < closingH)
            return false;

        //Si presume che un centro sportivo rimanga aperto più di un'ora, ma possa rimanere aperto 24h

        return openH != closingH || openH == 0; //se si raggiunge la seconda condizione dell'or, sicuramente
                                                //openH e closingH sono uguali, allora controlliamo se uno dei due
                                                //sia zero, perchè vuol dire che è un centro sportivo 24h 00:00->00:00
        }



    //All the setters and getters for this POJO

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSportCenterName(String sportCenterName) {
        this.sportCenterName = sportCenterName;
    }

    public void setSportCenterAddress(String sportCenterAddress) {
        this.sportCenterAddress = sportCenterAddress;
    }

    public void setNumOfFootballFields(int numOfFootballFields) {
        this.numOfFootballFields = numOfFootballFields;
    }

    public void setNumOfPadelCourts(int numOfPadelCourts) {
        this.numOfPadelCourts = numOfPadelCourts;
    }

    public void setNumOfBasketCourts(int numOfBasketCourts) {
        this.numOfBasketCourts = numOfBasketCourts;
    }

    public void setNumOfTennisCourts(int numOfTennisCourts) {
        this.numOfTennisCourts = numOfTennisCourts;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public void setOpeningMinutes(int openingMinutes) {
        this.openingMinutes = openingMinutes;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public void setClosingMinutes(int closingMinutes) {
        this.closingMinutes = closingMinutes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

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

    public int getOpeningMinutes() {
        return openingMinutes;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public int getClosingMinutes() {
        return closingMinutes;
    }
}
