package com.sportify.joinmatch;

import com.sportify.sportcenter.courts.TimeSlot;
import com.sportify.utilitiesui.UIController;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

public class JoinMatchViewController {
    public void showSettings() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings();
    }

    public void goToHome() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen();
    }

    public void showCreateMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch();
    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }

    public void pressMeButton(ActionEvent actionEvent) {
        JoinMatchController c = new JoinMatchController();
        List<TimeSlot> list = c.getJoinableMatch("Football");
        System.out.println("""
                        **RESULTS**
                """);
        for (TimeSlot t : list){
            System.out.println(t.getAvailableSpots() + "," + (t.getStartTime()).getHour() + "," + (t.getEndTime()).getHour());
        }


    }
}
