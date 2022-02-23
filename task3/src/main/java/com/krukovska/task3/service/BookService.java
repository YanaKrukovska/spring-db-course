package com.krukovska.task3.service;

import com.krukovska.task3.model.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<Book> findAllByAuthor(Long authorId);

    List<Book> findAllByAuthorGroup(List<Long> authorsId);

    List<Book> findAllByAtLeastOneAuthor(List<Long> authorsId);

    List<Book> findByPrice(BigDecimal price);

    List<Book> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo);

    List<Book> findByPublisher(String publisher);

    List<Book> findAllByGenre(Long genreId);

    List<Book> findAllByMultipleGenres(List<Long> genreIds);

    List<Book> findAllByAtLeastOneGenre(List<Long> genreIds);

    List<Book> findByPageAmount(long pageAmount);

    List<Book> findByPageAmountRange(long pageAmountFrom, long pageAmountTo);

    List<Book> findByText(String input);
}
