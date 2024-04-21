package com.ncodersalab.reactiveprogramming;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class BackPresureTest {

    @Test
    public void testBackPressure() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("integer = " + integer));
        numbers.onBackpressureDrop(value -> System.out.println("Drop value = " + value))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("completed");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }

                    @Override
                    protected void hookFinally(SignalType type) {
                        super.hookFinally(type);
                    }
                });
    }
}
