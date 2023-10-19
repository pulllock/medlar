package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class From {

    public static void main(String[] args) {
        new From().from();
    }

    public void from() {
        Integer[] arrays = {0, 1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(arrays).collect(Collectors.toList());

        Observable<Integer> fromArray = Observable.from(arrays);
        Observable<Integer> fromIterable = Observable.from(list);

        fromArray.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("from array: " + integer);
            }
        });

        fromIterable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("from list: " + integer);
            }
        });
    }
}
