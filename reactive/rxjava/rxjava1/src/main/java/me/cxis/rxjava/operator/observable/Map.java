package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class Map {

    public static void main(String[] args) {
        new Map().map();
    }

    public void map() {
        Observable<Integer> observable = Observable.just(1, 2, 3)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 10;
                    }
                });

        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
