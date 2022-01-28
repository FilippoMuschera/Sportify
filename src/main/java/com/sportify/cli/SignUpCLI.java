package com.sportify.cli;

import com.sportify.login.exceptions.LoginFailedException;
import com.sportify.signup.SignUpBean;
import com.sportify.signup.SignUpController;
import com.sportify.signup.exceptions.DifferentPasswordException;
import com.sportify.signup.exceptions.UserAlreadyExistsException;

import java.util.Scanner;

import static java.lang.System.*;

public class SignUpCLI {

    public void signUserUp() {
        while (this.setUpSignUpBean() > 0) {
            //continua ad eseguire la funzione finchè l'utente non riesce a registrarsi correttamente
        }

    }

    private int setUpSignUpBean(){
        out.println("Insert your email");
        Scanner scanner = new Scanner(in);
        String email = scanner.nextLine();

        out.println("Now insert your password");
        String password = scanner.nextLine();

        out.println("Insert your password again");
        String password2 = scanner.nextLine();

        out.println("Are you a Sport Center Owner? (y/n)");
        String owner = scanner.nextLine();
        boolean isOwner;
        isOwner = "y".equals(owner);

        SignUpBean bean = new SignUpBean();

        try {
            bean.setEmail(email);
            bean.setFirstPsw(password);
            bean.setSecondPsw(password2);
            bean.setOwner(isOwner);
        } catch (LoginFailedException e) {
            err.println("This email is not valid, try again with a different one");
            return 1;
        }

        SignUpController controller = new SignUpController();
        try {
            controller.signUpUser(bean);
            return 0;
        } catch (UserAlreadyExistsException e) {
            err.println("User already exists, restart the program and log in");
            System.exit(10);
        } catch (DifferentPasswordException e) {
            err.println(e.getMessage());
            return 1;
        }
        return 0;
    }

}
