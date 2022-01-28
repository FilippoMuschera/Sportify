package com.sportify.joinmatch;

import java.util.ArrayList;

public class ResultSetEntity {

    private ArrayList<ResultElement> elementsList = new ArrayList<>();

    public void addElement(ResultElement r){
        elementsList.add(r);
    }

    public ArrayList<ResultElement> getElements() {
        return this.elementsList;
    }

    public void setElementsList(ArrayList<ResultElement> elementsList) {
        this.elementsList = elementsList;
    }
}
