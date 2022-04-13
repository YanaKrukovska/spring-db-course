package com.krukovska.task3.service.impl;

import com.krukovska.task3.model.Book;
import com.krukovska.task3.model.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application.properties")
class BookCriteriaServiceImplTest {

    @Autowired
    private BookCriteriaServiceImpl bookCriteriaService;

    @Test
    void findAllByAuthorSuccess() {
        List<Book> books = bookCriteriaService.findAllByAuthor(1L);
        assertEquals(2, books.size());
    }

    @Test
    void findAllByAuthorNoBooks() {
        List<Book> books = bookCriteriaService.findAllByAuthor(8L);
        assertEquals(0, books.size());
    }

    @Test
    void findAllByAuthorGroupNoBooks() {
        List<Book> books = bookCriteriaService.findAllByAuthorGroup(asList(1L, 6L));
        assertEquals(0, books.size());
    }

    @Test
    void findAllByInPriceRangeMultipleFound() {
        List<Book> books = bookCriteriaService.findByPriceRange(BigDecimal.valueOf(100), BigDecimal.valueOf(1000));
        assertEquals(8, books.size());
    }

    @Test
    void findAllByAtLeastOneAuthorFromGroupNoResult() {
        List<Book> books = bookCriteriaService.findAllByAtLeastOneAuthor(asList(8L, 9L));
        assertEquals(0, books.size());
    }

    @Test
    void findAllByInPriceRangeOneResult() {
        List<Book> books = bookCriteriaService.findByPriceRange(BigDecimal.valueOf(84), BigDecimal.valueOf(85));
        assertEquals(1, books.size());
    }

    @Test
    void findAllByInPriceRangeNoResult() {
        List<Book> books = bookCriteriaService.findByPriceRange(BigDecimal.valueOf(1000), BigDecimal.valueOf(1200));
        assertEquals(0, books.size());
    }

    @Test
    void findAllByCertainPriceMultipleFound() {
        List<Book> books = bookCriteriaService.findByPrice(BigDecimal.valueOf(250));
        assertEquals(3, books.size());
    }

    @Test
    void findAllByCertainPriceOneResult() {
        List<Book> books = bookCriteriaService.findByPrice(BigDecimal.valueOf(85));
        assertEquals(1, books.size());
    }

    @Test
    void findAllByCertainPriceNoResult() {
        List<Book> books = bookCriteriaService.findByPrice(BigDecimal.valueOf(12345));
        assertEquals(0, books.size());
    }

    @Test
    void findAllByPublisherMultipleResult() {
        List<Book> books = bookCriteriaService.findByPublisher("Publisher 1");
        assertEquals(3, books.size());
    }

    @Test
    void findAllByPublisherOneResult() {
        List<Book> books = bookCriteriaService.findByPublisher("Publisher 5");
        assertEquals(1, books.size());
    }

    @Test
    void findAllByPublisherNoResult() {
        List<Book> books = bookCriteriaService.findByPublisher("Publisher 10");
        assertEquals(0, books.size());
    }

    @Test
    void findAllByGenreMultipleResult() {
        List<Book> books = bookCriteriaService.findAllByGenre(1L);
        assertEquals(4, books.size());
    }

    @Test
    void findAllByGenreNoResult() {
        List<Book> books = bookCriteriaService.findAllByGenre(6L);
        assertEquals(0, books.size());
    }

    @Test
    void findAllByPageAmountMultipleResult() {
        List<Book> books = bookCriteriaService.findByPageAmount(200);
        assertEquals(2, books.size());
    }

    @Test
    void findAllByPageAmountSingleResult() {
        List<Book> books = bookCriteriaService.findByPageAmount(272);
        assertEquals(1, books.size());
    }

    @Test
    void findAllByPageAmountNoResult() {
        List<Book> books = bookCriteriaService.findByPageAmount(6000);
        assertEquals(0, books.size());
    }

    @Test
    void findAllByPageAmountRangeMultipleResult() {
        List<Book> books = bookCriteriaService.findByPageAmountRange(200, 300);
        assertEquals(6, books.size());
    }

    @Test
    void findAllByPageAmountRangeSingleResult() {
        List<Book> books = bookCriteriaService.findByPageAmountRange(150, 169);
        assertEquals(1, books.size());
    }

    @Test
    void findAllByPageAmountRangeNoResult() {
        List<Book> books = bookCriteriaService.findByPageAmountRange(10, 50);
        assertEquals(0, books.size());
    }

    @Test
    void findByMultipleGenresNoResult() {
        List<Book> books = bookCriteriaService.findAllByMultipleGenres(asList(1L, 5L));
        assertEquals(0, books.size());
    }

    @Test
    void findByAtLeastOneGenreNoResult() {
        List<Book> books = bookCriteriaService.findAllByAtLeastOneGenre(singletonList(6L));
        assertEquals(0, books.size());
    }

    @Test
    void findByTextInDescriptionSuccess() {
        assertEquals(0, bookCriteriaService.findByText("romance").size());
        assertEquals(1, bookCriteriaService.findByText("ad").size());
        assertEquals(9, bookCriteriaService.findByText("the").size());
        assertEquals(0, bookCriteriaService.findByText("hello").size());
        assertEquals(1, bookCriteriaService.findByText("non").size());
    }

    @Test
    void findDynamic() {
        assertEquals(1, bookCriteriaService.findByParameters(
                asList(
                        new Condition("publisher", "Publisher 4", "and"),
                        new Condition("price", "250", "and")
                )).size());

        assertEquals(2, bookCriteriaService.findByParameters(
                asList(
                        new Condition("price", "348", "or"),
                        new Condition("pageAmount", "336", "or")
                )).size());

        assertEquals(0, bookCriteriaService.findByParameters(
                asList(
                        new Condition("price", "348", "and"),
                        new Condition("pageAmount", "336", "and")
                )).size());
    }

}