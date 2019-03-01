package me.cxis.gof.observer_pattern.example1;

public class ConcreteSubject extends Subject {

    private int originState = 0 ;

    public void changeStatus(int state) {
        if (state == originState) {
            System.out.println(this.getClass().getSimpleName() + ":state not change");
        } else {
            System.out.println(this.getClass().getSimpleName() + ":state changed");
            notifyThem();
        }
    }

    @Override
    public boolean notifyThem() {
        observers.forEach(Observer::update);
        return false;
    }
}
