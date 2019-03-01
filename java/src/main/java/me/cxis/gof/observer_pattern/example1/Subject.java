package me.cxis.gof.observer_pattern.example1;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    List<Observer> observers = new ArrayList<>();

    public boolean attach(Observer observer) {
        return observers.add(observer);
    }

    public boolean unattach(Observer observer) {
        return observers.remove(observer);
    }

    public abstract boolean notifyThem();
}
