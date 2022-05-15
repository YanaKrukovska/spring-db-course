package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.model.DriverEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DriverJdbcRepositoryTest extends BaseJdbcTest {

    @Test
    void findAll() {
        List<DriverEntity> result = driverRepository.findAll();
        assertEquals(4, result.size());
    }

    @Test
    void findByIdNotExists() {
        Optional<DriverEntity> result = driverRepository.findById(22222);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByCountryExists() {
        List<DriverEntity> result = driverRepository.findByCountry("France");
        assertEquals(2, result.size());
    }

    @Test
    void findByCountryNotExists() {
        List<DriverEntity> result = driverRepository.findByCountry("Ukraine");
        assertTrue(result.isEmpty());
    }

}