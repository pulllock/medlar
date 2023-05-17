package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

public class Just {

    public static void main(String[] args) {
        new Just().just();
    }

    public void just() {
        Observable<Long> justObservable = Observable.just(System.currentTimeMillis());

        justObservable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });

        justObservable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });
    }
}
