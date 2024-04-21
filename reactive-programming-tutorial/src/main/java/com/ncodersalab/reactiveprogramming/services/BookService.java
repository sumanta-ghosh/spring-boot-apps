package com.ncodersalab.reactiveprogramming.services;

import com.ncodersalab.reactiveprogramming.domain.Book;
import com.ncodersalab.reactiveprogramming.domain.BookInfo;
import com.ncodersalab.reactiveprogramming.domain.Review;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        }).log();
    }

    public Mono<Book> getBookById(long bookId) {
        Mono<BookInfo> bookInfo = bookInfoService.getBookById(bookId);
        Mono<List<Review>> reviews = reviewService.getReviews(bookId).collectList();
        return bookInfo.zipWith(reviews, Book::new).log();
    }
}
