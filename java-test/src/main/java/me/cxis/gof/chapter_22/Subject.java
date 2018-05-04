package me.cxis.gof.chapter_22;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng.xi on 2017-02-28 14:00.
 * 被观察者
 */
public abstract class Subject {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        this.observers.add(observer);
    }

    public void delObserver(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyOservers(){
        for(Observer observer : observers){
            observer.update();
        }
    }

}
