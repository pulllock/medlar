package fun.pullock.reactor.flux;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class Just {

    public static void main(String[] args) {
        new Just().just();
    }

    public void just() {
        Flux<String> flux = Flux.just("1", "2", "E");
        flux.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
