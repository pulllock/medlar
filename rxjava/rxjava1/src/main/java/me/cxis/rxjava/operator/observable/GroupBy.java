package me.cxis.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class GroupBy {

    public static void main(String[] args) {
        new GroupBy().groupBy();
    }

    public void groupBy() {
        Observable<GroupedObservable<Integer, Integer>> groupByObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer %2;
                    }
                });

        Observable<GroupedObservable<Integer, String>> groupByStringObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer % 2;
                    }
                }, new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "group by: " + integer;
                    }
                });

        groupByObservable.subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
            @Override
            public void call(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.count().subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("key: " + integerIntegerGroupedObservable.getKey() + " contains: " + integer);
                    }
                });
            }
        });

        groupByStringObservable.subscribe(new Action1<GroupedObservable<Integer, String>>() {
            @Override
            public void call(GroupedObservable<Integer, String> integerStringGroupedObservable) {
                if (integerStringGroupedObservable.getKey() == 0) {
                    integerStringGroupedObservable.subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            System.out.println(s);
                        }
                    });
                }
            }
        });
    }
}
