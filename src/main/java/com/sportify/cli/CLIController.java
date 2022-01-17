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
}
