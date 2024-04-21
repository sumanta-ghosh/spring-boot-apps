package com.ncodersalab.reactiveprogramming.services;

import com.ncodersalab.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {
    public Flux<BookInfo> getBooks() {
        return Flux.just(
                new BookInfo(1, "Book One", "Author One", "123"),
                new BookInfo(2, "Book Two", "Author Two", "234"),
                new BookInfo(3, "Book Three", "Author Three", "345")
        );
    }

    public Mono<BookInfo> getBookById(long bookId) {
        BookInfo bookInfo = new BookInfo(bookId, "Book " + bookId, "Author One", "123");
        return Mono.just(bookInfo);
    }
}
