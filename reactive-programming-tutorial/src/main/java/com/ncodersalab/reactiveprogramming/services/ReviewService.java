package com.ncodersalab.reactiveprogramming.services;

import com.ncodersalab.reactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;

public class ReviewService {
    public Flux<Review> getReviews(long bookId) {
        return Flux.just(
                new Review(1, bookId, 8.7, "Very good book"),
                new Review(2, bookId, 9.3, "Nice read"),
                new Review(3, bookId, 8.7, "Very good book"),
                new Review(4, bookId, 9.3, "Nice read")
        );
    }
}
