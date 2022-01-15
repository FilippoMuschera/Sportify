package com.sportify.user.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

   private List<Observer> observes = new ArrayList<>();

   private boolean state = false;

    public void attach(Observer o){
        observes.add(o);
    }

    public void detach(Observer o){
        observes.remove(o);
    }

    protected void notifyObservers(){
        for (Observer o : observes){
            o.update();
        }
        this.state = false;
    }

    public void setState(boolean s){
        this.state = s;
        notifyObservers();
    }

    public boolean hasChanged() {
        return state;
    }
}
