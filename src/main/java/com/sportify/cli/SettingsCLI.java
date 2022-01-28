package com.sportify.cli;

import com.sportify.settings.SettingsBean;
import com.sportify.settings.SettingsController;
import com.sportify.settings.exceptions.AddressNotValidException;
import com.sportify.user.UserEntity;
import com.sportify.user.UserPreferences;
import com.sportify.user.observer.Observer;

import java.util.Scanner;

import static java.lang.System.*;

public class SettingsCLI implements Observer {

    public void showSettingsCLI(){
        UserEntity.getInstance().getPreferences().attach(this);
        while (setUpSettingsBean() > 0){
            //esegue finchè l'utente non esce o non completa correttamente l'esecuzione
        }
        HomeScreenCLI.getInstance().showCLIHomeScreen();


    }


    private int setUpSettingsBean() {

        SettingsBean bean = new SettingsBean();
        Scanner scanner = new Scanner(in);
        out.println("Select you radius of interest: 3 km, 5 km, 10 km (default is 5 km) (OR \"exit\" TO RETURN TO HOME)");
        String radius = scanner.nextLine();
        if (radius.equals("exit"))
            return 0;
        int r = Integer.parseInt(radius);
        if (r != 3 && r!= 5 && r!= 10)
            r = 5; //se la scelta è scorretta si setta il default
        bean.setRadius(r);
        out.println("Do you want to receive notifications from the app via email? (y/n)");
        String notificationsString = scanner.nextLine();
        boolean notifications = notificationsString.equals("y");
        bean.setNotifications(notifications);
        String sportsMessage = ("""
                Now you have to select what sports you want to add to your favourites. We currently support:
                
                Football (currently = %s)
                Padel (currently = %s)
                Basket (currently = %s)
                Tennis (currently = %s)
                
                For each sport, write "1" if you want it to be from your favourites, or "0" if you don't.
                
                Example: 1 0 0 1 means that Football and Tennis are the sports you selected.             
                
                 """);

        UserEntity user = UserEntity.getInstance();
        UserPreferences preferences = user.getPreferences();
        sportsMessage = String.format(sportsMessage, preferences.getFootball(), preferences.getPadel(),
                preferences.getBasket(), preferences.getTennis());
        out.println(sportsMessage);
        String input = scanner.nextLine();
        String[] values = input.split(" ");
        if (values.length != 4){
            err.println("You have to write four numbers, one for each sport!");
            return 1;
        }
        bean.setFootball(values[0].equals("1"));
        bean.setPadel(values[1].equals("1"));
        bean.setBasket(values[2].equals("1"));
        bean.setTennis(values[3].equals("1"));

        out.println("""
                Now let us know what's your address (more info about how we use it are in the FAQs)
                Write your street name and your house number. Example: Via del Politecnico 1.
                """);
        bean.setAddress(scanner.nextLine());
        out.println("Now tell us your city");
        bean.setCity(scanner.nextLine());
        out.println("And now your ZIP code (es.: 00133)");

        try {
            bean.setCap(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            err.println(e.getMessage());
            return 1;
        }
        SettingsController controller = new SettingsController();
        try {
            controller.saveSettings(bean);
        } catch (AddressNotValidException e){
            err.println("Invalid address! Try again");
            return 1;
        }


        return 0;
    }


    @Override
    public void update() {
        UserPreferences preferences = UserEntity.getInstance().getPreferences();
        String settingsUpdate = ("""
                        *** Your Settings are now the following ***
                        
                Radius of Interest = %d
                Notifications = %s
                Football = %s
                Padel = %s
                Basket = %s
                Tennis = %s
                Address = %s
                
           
                """);
        settingsUpdate = String.format(settingsUpdate, preferences.getSortingDistance(), preferences.isNotifications(),
                preferences.getFootball(), preferences.getPadel(), preferences.getBasket(), preferences.getTennis(),
                preferences.getUserAddress());
        out.println(settingsUpdate);
    }
}
