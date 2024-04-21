package com.ncodersalab.reactiveprogramming.services;

import com.ncodersalab.reactiveprogramming.domain.Book;
import com.ncodersalab.reactiveprogramming.domain.BookInfo;
import com.ncodersalab.reactiveprogramming.domain.Review;
import com.ncodersalab.reactiveprogramming.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        Flux<BookInfo> allBookReviews = bookInfoService.getBooks();
        return allBookReviews.flatMap(bookInfo -> {
            Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews.map(review -> new Book(bookInfo, review));
        }).onErrorMap(throwable -> {
                    log.info("Original Exception -> {}", throwable.getMessage());
                    return new BookException("Exception happened!!!!");
                }
        ).log();
    }

    public Flux<Book> getBooksRetry() {
        Flux<BookInfo> allBookReviews = bookInfoService.getBooks();
        return allBookReviews.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                }).onErrorMap(throwable -> {
                            log.info("Original Exception -> {}", throwable.getMessage());
                            return new BookException("Exception happened!!!!");
                        }
                )
                .retry(1)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
        var retrySpecs = getRetryBackoffSpec();

        Flux<BookInfo> allBookReviews = bookInfoService.getBooks();
        return allBookReviews.flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                }).onErrorMap(throwable -> {
                            log.info("Original Exception -> {}", throwable.getMessage());
                            return new BookException("Exception happened!!!!");
                        }
                )
                .retryWhen(retrySpecs)
                .log();
    }

    private static RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.backoff(
                        3,
                        Duration.ofMillis(100)
                )
                //.filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow(
                        (retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure())
                );
    }

    public Mono<Book> getBookById(long bookId) {
        Mono<BookInfo> bookInfo = bookInfoService.getBookById(bookId);
        Mono<List<Review>> reviews = reviewService.getReviews(bookId).collectList();
        return bookInfo.zipWith(reviews, Book::new).log();
    }
}
