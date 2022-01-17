package com.sportify.cli;

import com.sportify.login.LogInBean;
import com.sportify.login.LogInController;
import com.sportify.login.exceptions.EmailNotValidException;
import com.sportify.login.exceptions.IncorrectPasswordException;
import com.sportify.login.exceptions.UserNotFoundException;
import com.sportify.signup.SignUpBean;

import java.io.Console;
import java.util.Scanner;

import static java.lang.System.*;

public class LogInCLI {

    public static void main(String[] args) {


        while (logIn() > 0){
            //Finchè non legge un valore negativo, continua ad eseguire il metodo per il login
        }
        //Se non sono state generate eccezioni carica la HomeScreen della CLI
        ViewControllerCLI viewControllerCLI = ViewControllerCLI.getInstance();
        viewControllerCLI.showCLIHomeScreen();



    }

    private static int logIn() {
        out.println("Insert your email to login, write \"signup\" to register, or \"exit\" to exit the program.");
        Scanner scanner = new Scanner(in);
        String email = scanner.nextLine();


        if ("exit".equals(email))
            return -1;
        if ("signup".equals(email)) {
            SignUpCLI signup = new SignUpCLI();
            signup.signUserUp();
        }

        out.println("Now insert your password");
        String password = scanner.nextLine();

        LogInBean bean = new LogInBean();
        bean.setEmail(email);
        bean.setPassword(password);
        LogInController controller = new LogInController();

        try {
            controller.logInUser(bean);
        } catch (UserNotFoundException e) {
            err.println("User not found, type \"signup\" to register!");
            return 1;
        } catch (IncorrectPasswordException e) {
            err.println("Incorrect password, try again");
            return 1;

        }
        try {
            bean.validateInput();
        } catch (EmailNotValidException e) {
            err.println("Invalid email, try again");
            return 1;
        }

        return 0;
    }
}
