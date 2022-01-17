package com.sportify.cli;

import com.sportify.user.UserEntity;

import java.util.Scanner;

import static java.lang.System.*;

public class ViewControllerCLI {
    private static ViewControllerCLI instance = null;
    private UserEntity user;

    private ViewControllerCLI(){}

    public static ViewControllerCLI getInstance(){
        if (ViewControllerCLI.instance == null)
            instance = new ViewControllerCLI();
        return instance;
    }

    public void setUser(UserEntity u){
        user = u;
    }

    public UserEntity getUser() {
        return user;
    }

    public void showCLIHomeScreen() {
        int selectedOption;
        while ((selectedOption = showOptions()) < 0) {
            //esegue showOptions finchè l'utente non sceglie un'opzione corretta o non termina il programma
        }
        switch (selectedOption){
            case 5 -> System.exit(0); //Il programma termina su scelta dell'utente
            default -> {/* Non è necessario gestire altri casi perchè sono già gestiti del ciclo while precedente */}


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
                5. Quit the application
                """);
        Scanner scanner = new Scanner(in);
        int selectedOption = scanner.nextInt();
        if (selectedOption > 5 || selectedOption < 1) {
            err.println("Invalid option, try again!");
            return -1;
        }


        return selectedOption;
        }

    }



