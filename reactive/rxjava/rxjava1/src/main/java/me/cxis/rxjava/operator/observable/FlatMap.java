package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

public class FlatMap {

    public static void main(String[] args) {
        new FlatMap().flatMap();
    }

    public void flatMap() {
        Observable<String> flatMapObservable = Observable.just(1, 2, 3)
                .flatMap(new Func1<Integer, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Integer integer) {
                        return Observable.just("flat map: " + integer);
                    }
                });

        Observable<String> flatMapIterableObservable = Observable.just(1, 2, 3)
                .flatMapIterable(new Func1<Integer, Iterable<? extends String>>() {
                    @Override
                    public Iterable<? extends String> call(Integer integer) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("flat map iterable: " + integer);
                        }
                        return list;
                    }
                });

        flatMapObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        flatMapIterableObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}
