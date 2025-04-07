package com.programmingtechie.orderservice.utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ReactiveUtil {
    public static void main(String[] args) throws InterruptedException {
        wordStreamReactive().log()
                .subscribe(w -> System.out.println(w));
//                .subscribeWith(new MySubcriber());

    }

    public static Flux<String> wordStreamReactive() {
        return Flux.just("Hello", "Reactive", "World");
    }


}
class MySubcriber implements Subscriber<String> {

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe() method called");

        subscription.request(10);
    }

    @Override
    public void onNext(String s) {
        System.out.println("onNext() method called");
        System.out.println(s);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError() method called");
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete() method called");
    }
}