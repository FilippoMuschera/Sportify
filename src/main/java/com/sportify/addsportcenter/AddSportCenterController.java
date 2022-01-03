package com.sportify.addsportcenter;

import com.sportify.sportcenter.AddSportCenterDAO;
import com.sportify.sportifyui.UIController;
import com.sportify.user.UserEntity;

public class AddSportCenterController {
    public void addSportCenter(AddSportCenterBean addSportCenterBean) {
        UserEntity user = UIController.getUIControllerInstance().getUser();

        //TODO maybe logica per calcolo coordinate campo andrà qui, nel controller

        //TODO creare l'istanza del campo sportivo che la dao deve poi caricare sul DB

        AddSportCenterDAO dao = new AddSportCenterDAO();
        dao.addSCToDB();

        //TODO gestione delle eccezioni che la classe dao può generare (es.: centro sportivo già presente ecc...)
    }
}
