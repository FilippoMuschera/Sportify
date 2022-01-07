package com.sportify.bookmatch.statemachine;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public interface BMStateInterface {

    public ObservableList displayView();

    public void entry(String string);

}
