package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Buffer {

    public static void main(String[] args) {
        new Buffer().buffer();
    }

    public void buffer() {
        Observable<List<Integer>> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(2, 3);
        Observable<List<Long>> timeObservable = Observable.interval(1, TimeUnit.SECONDS)
                .buffer(2, TimeUnit.SECONDS);

        observable.subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("buffer: " + integers);
            }
        });

        timeObservable.subscribe(new Action1<List<Long>>() {
            @Override
            public void call(List<Long> longs) {
                System.out.println("buffer time: " + longs);
            }
        });
    }
}
