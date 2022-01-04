package com.sportify.utilitiesui;

import com.sportify.user.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;


public class SettingsViewController {

    @FXML
    private ToggleButton radius3;
    @FXML
    private ToggleButton radius5;
    @FXML
    private ToggleButton radius10;
    @FXML
    private ToggleSwitch notificationsSwitch;
    @FXML
    private CheckBox footballCB;
    @FXML
    private CheckBox padelCB;
    @FXML
    private CheckBox tennisCB;
    @FXML
    private CheckBox basketCB;





    public void showHome(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showHomeScreen(actionEvent);
    }

    public void goToJoinScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showJoinMatch(actionEvent);
    }

    public void goToCreateScreen(ActionEvent actionEvent) throws IOException {
        UIController uic = UIController.getUIControllerInstance();
        uic.showCreateMatch(actionEvent);
    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }

    @FXML
    public void initialize(){

        UserEntity user = UIController.getUIControllerInstance().getUser();
        int radius = user.getPreferences().getSortingDistance();
        switch (radius) {
            case 10 -> radius10.setSelected(true);
            case 5 -> radius5.setSelected(true);
            case 3 -> radius3.setSelected(true);
            default -> throw new IllegalArgumentException("Radius value was wrong or null");
        }
        boolean notifications = user.getPreferences().isNotifications();
        notificationsSwitch.setSelected(notifications);

       footballCB.setSelected(user.getPreferences().getSports().get("Football"));
       padelCB.setSelected(user.getPreferences().getSports().get("Padel"));
       basketCB.setSelected(user.getPreferences().getSports().get("Basket"));
       tennisCB.setSelected(user.getPreferences().getSports().get("Tennis"));



    }
}
