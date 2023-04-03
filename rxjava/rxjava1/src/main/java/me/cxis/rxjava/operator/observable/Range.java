package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

public class Range {

    public void range() {
        Observable.range(10, 5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

}
