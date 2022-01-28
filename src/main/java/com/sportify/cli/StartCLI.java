package com.sportify.cli;

import com.sportify.login.LogInBean;
import com.sportify.login.LogInController;
import com.sportify.login.exceptions.LoginFailedException;
import com.sportify.login.exceptions.UserNotFoundException;
import java.util.Scanner;

import static java.lang.System.*;

public class StartCLI {


    private static boolean isLogged = false;

    public static void main(String[] args) {



        while (!isLogged){
            //Finchè non legge un valore negativo, continua ad eseguire il metodo per il login
            logUser();
        }
        //Se non sono state generate eccezioni carica la HomeScreen della CLI
        CLIController cliController = CLIController.getIstance();
        cliController.loadHomeScreen();

    }

    private static void logUser() {
        out.println("Insert your email to login, write \"signup\" to register, or \"exit\" to exit the program.");
        Scanner scanner = new Scanner(in);
        String email = scanner.nextLine();


        if ("exit".equals(email))
            System.exit(0); //Uscita dal programma su richiesta dell'utente
        if ("signup".equals(email)) {
            CLIController c = CLIController.getIstance();
            c.showSignUp();
            isLogged = true;
            return;
        }

        out.println("Now insert your password");
        String password = scanner.nextLine();

        LogInBean bean = new LogInBean();
        try {
            bean.setEmail(email);
        } catch (LoginFailedException e) {
            err.println(e.getMessage());
            isLogged = false;
            return;
        }
        bean.setPassword(password);
        LogInController controller = new LogInController();

        try {
            controller.logInUser(bean);
            isLogged = true;
        } catch (UserNotFoundException e) {
            err.println("User not found, type \"signup\" to register!");
            isLogged = false;
        } catch (LoginFailedException e) {
            err.println(e.getMessage());
            isLogged = false;

        }



    }


}
