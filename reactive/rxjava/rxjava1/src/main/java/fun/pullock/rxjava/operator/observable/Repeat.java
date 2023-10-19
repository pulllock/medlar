package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

public class Repeat {

    public static void main(String[] args) {
        new Repeat().repeat();
    }

    public void repeat() {
        Observable<Integer> repeatObservable = Observable.just(1, 2, 3).repeat(3);

        repeatObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("repeat: " + integer);
            }
        });
    }
}
