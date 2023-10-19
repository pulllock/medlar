package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

public class Timer {

    public static void main(String[] args) {
        new Timer().timer();
    }

    public void timer() {
        Observable<Long> observable = Observable.timer(1, TimeUnit.SECONDS);
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("timer: " + aLong);
            }
        });
    }
}
