package com.sportify.settings;

import com.sportify.user.UserEntity;
import com.sportify.utilitiesui.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;


public class SettingsViewController {

    @FXML
    private TextField addrText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField capText;
    @FXML
    private Label saveLabel;
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

       footballCB.setSelected(user.getPreferences().getFootball());
       padelCB.setSelected(user.getPreferences().getPadel());
       basketCB.setSelected(user.getPreferences().getBasket());
       tennisCB.setSelected(user.getPreferences().getTennis());

       String[] addressArray = user.getPreferences().getUserAddress().split(", ");
       addrText.setText(addressArray[0]);
       cityText.setText(addressArray[1]);
       capText.setText(addressArray[2]);

       saveLabel.setTextFill(Color.rgb(33,37,41));
       saveLabel.setText("Your current address is: " + user.getPreferences().getUserAddress());



    }

    public void saveSettings() {
        SettingsBean bean = new SettingsBean();
        int radius = 0;
        if (radius3.isSelected())
            radius = 3;
        else if (radius5.isSelected())
            radius = 5;
        else if (radius10.isSelected())
            radius = 10;
        bean.setRadius(radius);
        bean.setNotifications(notificationsSwitch.isSelected());
        bean.setBasket(basketCB.isSelected());
        bean.setFootball(footballCB.isSelected());
        bean.setTennis(tennisCB.isSelected());
        bean.setPadel(padelCB.isSelected());
        bean.setAddress(addrText.getText());
        bean.setCity(cityText.getText());
        bean.setCap(capText.getText());
        try {
            bean.saveSettings();
            this.initialize();
        } catch (IllegalArgumentException e){
            saveLabel.setTextFill(Color.RED);
            saveLabel.setText("Invalid CAP, try again!");
        }


    }
}
