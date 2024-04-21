package com.ncodersalab.reactiveprogramming;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamTest {
    @SneakyThrows
    @Test
    public void hotStreamTest() {
        var numbers = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(1000));

        ConnectableFlux<Integer> publisher = numbers.publish();
        publisher.connect();

        publisher.subscribe(integer -> System.out.println("Sub #1 = " + integer));
        System.out.println("Going to sleep for 4 sec");
        Thread.sleep(4000);
        publisher.subscribe(integer -> System.out.println("Sub #2 = " + integer));
        Thread.sleep(10000);
    }
}
