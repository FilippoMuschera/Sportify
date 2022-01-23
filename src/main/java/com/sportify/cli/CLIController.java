package com.sportify.cli;

public class CLIController {

    private static CLIController istance = null;

    public static CLIController getIstance() {
        if (istance == null)
            istance = new CLIController();
        return istance;
    }

    private CLIController(){}

    public void loadHomeScreen(){
        HomeScreenCLI homeScreenCLI = HomeScreenCLI.getInstance();
        homeScreenCLI.showCLIHomeScreen();


    }

    public void showSignUp(){
        SignUpCLI signUpCLI = new SignUpCLI();
        signUpCLI.signUserUp();
    }

    public void showSettings(){
        SettingsCLI settingsCLI = new SettingsCLI();
        settingsCLI.showSettingsCLI();

    }

    public void showAddSportCenterCLI() {
        AddSportCenterCLI addSportCenterCLI = new AddSportCenterCLI();
        addSportCenterCLI.addSportCenterFromCLI();
    }

    public void showBookMatchCLI(){
        BookMatchCLI bookMatchCLI = new BookMatchCLI();
        bookMatchCLI.startBookMatch();
    }
}
