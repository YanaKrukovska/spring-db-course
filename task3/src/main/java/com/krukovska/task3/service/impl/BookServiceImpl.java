package com.krukovska.task3.service.impl;

import com.krukovska.task3.model.Book;
import com.krukovska.task3.repository.BookRepository;
import com.krukovska.task3.service.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAllByAuthor(Long authorId) {
        return repository.findAuthorBook(authorId);
    }

    @Override
    public List<Book> findAllByAuthorGroup(List<Long> authorsId) {
        return repository.findGroupAuthorsBook(authorsId, authorsId.size());
    }

    @Override
    public List<Book> findAllByAtLeastOneAuthor(List<Long> authorsId) {
        return repository.findByAtLeastOneGroupAuthor(authorsId);
    }

    @Override
    public List<Book> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        return repository.findByPrice(priceFrom, priceTo);
    }

    @Override
    public List<Book> findByPrice(BigDecimal price) {
        return repository.findByPrice(price);
    }

    @Override
    public List<Book> findByPublisher(String publisher) {
        return repository.findByPublisher(publisher);
    }

    @Override
    public List<Book> findAllByGenre(Long genreId) {
        return repository.findAllByGenre(genreId);
    }

    @Override
    public List<Book> findAllByMultipleGenres(List<Long> genreIds) {
        return repository.findAllByMultipleGenres(genreIds, genreIds.size());
    }

    @Override
    public List<Book> findAllByAtLeastOneGenre(List<Long> genreIds) {
        return repository.findAllByAtLeastOneGenre(genreIds);
    }

    @Override
    public List<Book> findByPageAmount(long pageAmount) {
        return repository.findByPageAmount(pageAmount);
    }

    @Override
    public List<Book> findByPageAmountRange(long pageAmountFrom, long pageAmountTo) {
        return repository.findByPageAmountRange(pageAmountFrom, pageAmountTo);
    }

    @Override
    public List<Book> findByText(String input) {
        return repository.findByText(input.toLowerCase());
    }

}
