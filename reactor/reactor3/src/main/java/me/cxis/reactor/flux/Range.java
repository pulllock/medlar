package me.cxis.reactor.flux;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class Range {

    public static void main(String[] args) {
        new Range().range();
    }

    public void range() {
        Flux<Integer> flux = Flux.range(1, 3);
        flux.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
