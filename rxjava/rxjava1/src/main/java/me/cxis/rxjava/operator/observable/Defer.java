package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

public class Defer {

    public static void main(String[] args) {
        new Defer().defer();
    }

    public void defer() {
        Observable<Long> deferObservable = Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return Observable.just(System.currentTimeMillis());
            }
        });

        deferObservable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });

        deferObservable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });
    }
}
