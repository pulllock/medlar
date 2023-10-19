package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

public class Interval {

    public static void main(String[] args) {
        new Interval().interval();
    }

    public void interval() {
        Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS)
                // .observeOn(ImmediateScheduler.INSTANCE)
                ;

        intervalObservable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }
        });
    }
}
