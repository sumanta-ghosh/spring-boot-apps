package com.ncodersalab.reactiveprogramming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .filter(f -> f.length() > number);
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .flatMap(f -> Flux.just(f.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .flatMap(f -> Flux.just(f.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )).log();
    }

    public Flux<String> fruitsFluxTransform(int number) {

        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .transform(filterData).log();
    }

    public Flux<String> fruitsFluxTransformDefaultIFEmpty(int number) {

        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Apple", "Banana", "Orange"))
                .transform(filterData)
                .defaultIfEmpty("NA")
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        Flux<String> list1 = Flux.just("Mango", "Apple", "Banana", "Orange");
        Flux<String> list2 = Flux.just("Pineapple");
        return Flux.concat(list1, list2).log();
    }

    public Flux<String> fruitsFluxConcatWith() {
        Flux<String> list1 = Flux.just("Mango", "Apple", "Banana", "Orange");
        Flux<String> list2 = Flux.just("Pineapple");
        return list1.concatWith(list2).log();
    }

    public Flux<String> fruitsFluxZip() {
        Flux<String> list1 = Flux.just("Sumanta", "Amit");
        Flux<String> list2 = Flux.just("Ghosh", "Singh");
        return Flux.zip(list1, list2, (first, second) -> first + " " + second).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        Flux<String> list1 = Flux.just("Sumanta", "Amit", "Roy");
        Flux<String> list2 = Flux.just("Ghosh", "Singh");
        return list1.zipWith(list2, (first, second) -> first + " " + second).log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        Flux<String> list0 = Flux.just("Mr.", "Sri.");
        Flux<String> list1 = Flux.just("Sumanta", "Amit");
        Flux<String> list2 = Flux.just("Ghosh", "Singh");

        return Flux.zip(list0, list1, list2)
                .map(o -> o.getT1() + " " + o.getT2() + " " + o.getT3()).log();
    }

    public Mono<String> fruitsMono() {
        return Mono.just("Apple").log();
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitsFluxMap().subscribe(System.out::println);

        fluxAndMonoServices.fruitsMono().subscribe(System.out::println);
    }
}
