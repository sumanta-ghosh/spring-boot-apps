package com.ncodersalab.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {
    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitFlux)
                .expectNext("Mango", "Apple", "Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitMono = fluxAndMonoServices.fruitsMono();
        StepVerifier.create(fruitMono)
                .expectNext("Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxMap();
        StepVerifier.create(friutsFlux)
                .expectNext("MANGO", "APPLE", "BANANA", "ORANGE")
                .verifyComplete();

    }

    @Test
    void fruitsFluxFilter() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(friutsFlux)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(friutsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(friutsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxTransform(5);
        StepVerifier.create(friutsFlux)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIFEmpty() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxTransformDefaultIFEmpty(10);
        StepVerifier.create(friutsFlux)
                .expectNext("NA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxZip();
        StepVerifier.create(friutsFlux)
                .expectNext("Sumanta Ghosh", "Amit Singh")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxZipWith();
        StepVerifier.create(friutsFlux)
                .expectNext("Sumanta Ghosh", "Amit Singh")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var friutsFlux = fluxAndMonoServices.fruitsFluxZipTuple();
        StepVerifier.create(friutsFlux)
                .expectNext("Mr. Sumanta Ghosh", "Sri. Amit Singh")
                .verifyComplete();
    }
}