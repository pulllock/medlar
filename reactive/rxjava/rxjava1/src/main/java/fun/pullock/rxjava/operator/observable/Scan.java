package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

public class Scan {

    public static void main(String[] args) {
        new Scan().scan();
    }

    public void scan() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(2);
        }

        Observable<Integer> observable = Observable.from(list)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer * integer2;
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
