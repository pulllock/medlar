package fun.pullock.rxjava.operator.observable;

import rx.Observable;
import rx.functions.Action1;

public class Cast {

    public static void main(String[] args) {
        new Cast().cast();
    }

    public void cast() {
        Observable<Dog> observable = Observable.just(getAnimal())
                .cast(Dog.class);

        observable.subscribe(new Action1<Dog>() {
            @Override
            public void call(Dog dog) {
                System.out.println(dog.getName());
            }
        });
    }

    Animal getAnimal() {
        return new Dog();
    }

    class Animal {
        protected String name = "Animal";

        Animal() {
            System.out.println("create " + name);
        }

        String getName() {
            return name;
        }
    }

    class Dog extends Animal {
        Dog() {
            name = getClass().getSimpleName();
            System.out.println("create " + name);
        }
    }
}
