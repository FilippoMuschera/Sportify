package com.sportify.cli;

import com.sportify.addsportcenter.AddSportCenterBean;
import com.sportify.addsportcenter.AddSportCenterController;
import com.sportify.sportcenter.exceptions.SportCenterException;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class AddSportCenterCLI {

    public void addSportCenterFromCLI(){
        int r;
        while ((r = this.getSportCenterInfo()) > 0){
            //richiedi le informazioni per lo sport center da aggiungere
            if (r == 2){ //l'utente ha scelto di uscire dallo use case, lo si riporta alla home screen
                CLIController controller = CLIController.getIstance();
                controller.loadHomeScreen(); //ritorna alla home screen
            }

        }

        //Quando l'aggiunta ha avuto successo, si dà conferma all'utente
        out.println("Sport Center added successfully!");
        CLIController controller = CLIController.getIstance();
        controller.loadHomeScreen(); //ritorna alla home screen
    }

    private int getSportCenterInfo(){
        AddSportCenterBean bean = new AddSportCenterBean();
        Scanner s = new Scanner(in);
        out.println("Insert the name of your sport center OR \"exit\" TO RETURN TO HOME");
        String scName = s.nextLine();
        if (scName.equals("exit")){
            return 2;
        }
        bean.setSportCenterName(scName);
        out.println("""
    Insert the address of your sport center. Please be careful to use the following format: streetName houseNumber, City, ZIP code.
    Example: Via del Politecnico 1, Roma, 00133
    (Make sure to use commas!)
                    """);
        bean.setSportCenterAddress(s.nextLine());
        ArrayList<Integer> courtsNumber = new ArrayList<>();
        String[] sports = {"Football", "Padel", "Basket", "Tennis"};
        for (String sport : sports) {
            out.println("How many " + sport +" fields/courts does your sport center have?");
            courtsNumber.add(s.nextInt());
        }

        bean.setNumOfFootballFields(courtsNumber.get(0));
        bean.setNumOfPadelCourts(courtsNumber.get(1));
        bean.setNumOfBasketCourts(courtsNumber.get(2));
        bean.setNumOfTennisCourts(courtsNumber.get(3));

        out.println("At what time does you sport center open? (Insert only the hour in 24h format, ex.: 9");
        bean.setOpeningHour(s.nextInt());
        out.println("At what time does you sport center close? (Insert only the hour in 24h format, ex.: 18");
        bean.setClosingHour(s.nextInt());


        try {
            AddSportCenterController controller = new AddSportCenterController();
            controller.addSportCenter(bean);
            //Se non si verificano eccezioni, l'aggiunta del campo ha avuto successo
            return 0; //successo
             } catch (NumberFormatException e){
               err.println("One of the fields that requires a number is empty or wrong");
               return 1; //errore

             } catch (IllegalArgumentException | SportCenterException e){
               err.println(e.getMessage());
               return 1; //errore
    }


    }
}
