package com.sportify.cli;

import com.sportify.user.UserEntity;

import java.util.Scanner;

import static java.lang.System.*;

public class HomeScreenCLI {
    private static HomeScreenCLI instance = null;
    private final UserEntity user = UserEntity.getInstance();

    private HomeScreenCLI(){}

    public static HomeScreenCLI getInstance(){
        if (HomeScreenCLI.instance == null)
            instance = new HomeScreenCLI();
        return instance;
    }

    public void showCLIHomeScreen() {
        int selectedOption;
        while ((selectedOption = showOptions()) < 0) {
            //esegue showOptions finchè l'utente non sceglie un'opzione corretta o non termina il programma
        }
        switch (selectedOption){
            case 1 : {
                if (user.getType().equals("Player")) {
                    err.println("Your account is not of the \"Owner\" kind, you can't add a sport center");
                    CLIController c = CLIController.getIstance();
                    c.loadHomeScreen();
                    }
                else {
                    CLIController c = CLIController.getIstance();
                    c.showAddSportCenterCLI();
                }
                break;

            }

            case 2: {
                CLIController c = CLIController.getIstance();
                c.showSettings();
                break;
            }

            case 3 :{
                CLIController c = CLIController.getIstance();
                c.showBookMatchCLI();
                break;
            }

            case 5: {
                showFaq();
                break;
            }

            case 6 : System.exit(0); //Il programma termina su scelta dell'utente
                     break;
            default : {/* Non è necessario gestire altri casi perchè sono già gestiti del ciclo while precedente */}


        }
    }

    private int showOptions() {
        out.println("""
                                        ***WELCOME TO SPORTIFY CLI***
                
                Select what you want to do (insert the number):
                1. AddSportCenter (not available if you're not an "Owner")
                2. Change your Settings
                3. Book a Match
                4. Join a Match
                5. show FAQs
                6. Quit the application
                """);
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > 6 || selectedOption < 1) {
            err.println("Invalid option, try again!");
            return -1;
        }


        return selectedOption;
        }

    private void showFaq(){
        out.println("""
                • How is my address used?
                                
                Your address is the point from where is calculated your radius of interest. 
                It can be your home address, your working-place address, or your current address. 
                It's up to you!
                                
                • What is a "Joinable Match"?
                                
                A joinable match is a match of the selected sport, where the organizer is still looking 
                for people to play with. If you'd like you can join the match to play with other people.
                                
                • What is my "Radius of Interest"?
                                
                Is the maximum distance you're willing to travel to reach a sports center. 
                We will only suggest you sports centers that are within this radius.
                                
                • Can I add my sports center to the app?
                                
                Yes, if you are the owner of a sports center and you registered in the app as such, you 
                can click on the "+" button on the bottom-left-hand side in the Home Screen, 
                fill the form, and then your sports center will be added to our database.""");

        showCLIHomeScreen();
    }
    }



