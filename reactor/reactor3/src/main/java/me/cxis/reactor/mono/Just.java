package me.cxis.reactor.mono;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Just {

    public static void main(String[] args) {
        new Just().just();
    }

    public void just() {
        Mono<String> mono = Mono.just("E");
        mono.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
