package com.krukovska.task3.service.impl;

import com.krukovska.task3.model.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application.properties")
class BookCriteriaServiceImplTest {

    @Autowired
    private BookCriteriaServiceImpl bookCriteriaService;

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