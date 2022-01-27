package com.sportify.addsportcenter;

import com.sportify.sportcenter.exceptions.SportCenterException;
import com.sportify.utilitiesui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
public class AddSportCenterView {

    @FXML
    private TextField sportCenterAddress;
    @FXML
    private TextField sportCenterName;
    @FXML
    private TextField openingH;
    @FXML
    private TextField closingH;
    @FXML
    private TextField nFootball;
    @FXML
    private TextField nPadel;
    @FXML
    private TextField nBasket;
    @FXML
    private TextField nTennis;
    @FXML
    private Label addCourtPopupLabel;

    @FXML
    private Label outputStateLabel;


    public void showInfoPopUp() {
        addCourtPopupLabel.setOpacity(1);
    }

    public void hideInfoPopUp() {addCourtPopupLabel.setOpacity(0); }

    public void goToSettings() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showSettings();
    }

    public void goToHome() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showHomeScreen();
    }

    public void goToJoinMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showJoinMatch();
    }

    public void goToCreateMatch() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showCreateMatch();
    }

    public void addSportCenterToDB() {

        try {
            AddSportCenterBean bean = new AddSportCenterBean();
            bean.setSportCenterName(sportCenterName.getText());
            bean.setSportCenterAddress(sportCenterAddress.getText());
            bean.setOpeningHour(Integer.parseInt(openingH.getText()));
            bean.setClosingHour(Integer.parseInt(closingH.getText()));
            bean.setNumOfFootballFields(Integer.parseInt(nFootball.getText()));
            bean.setNumOfPadelCourts(Integer.parseInt(nPadel.getText()));
            bean.setNumOfBasketCourts(Integer.parseInt(nBasket.getText()));
            bean.setNumOfTennisCourts(Integer.parseInt(nTennis.getText()));

            AddSportCenterController controller = new AddSportCenterController();
            controller.addSportCenter(bean);

            //Se non vengono generate eccezioni, scrivo sulla label il risultato positivo del salvataggio dello sport center

            outputStateLabel.setText("Sport Center added successfully!");
            outputStateLabel.setTextFill(Color.GREEN);
            outputStateLabel.setOpacity(1);


        } catch (NumberFormatException e){
            outputStateLabel.setText("One of the fields that requires a number is empty or wrong");
            outputStateLabel.setTextFill(Color.RED);
            outputStateLabel.setOpacity(1);
        } catch (IllegalArgumentException | SportCenterException e){
            outputStateLabel.setText(e.getMessage());
            outputStateLabel.setTextFill(Color.RED);
            outputStateLabel.setOpacity(1);
        }


    }

    public void launchFaq() throws IOException {
        UIController c = UIController.getUIControllerInstance();
        c.showFaqs();
    }
}
