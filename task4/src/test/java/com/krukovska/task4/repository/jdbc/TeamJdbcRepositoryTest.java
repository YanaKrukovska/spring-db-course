package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.model.TeamEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamJdbcRepositoryTest extends BaseJdbcTest {

    @Test
    void findAll() {
        List<TeamEntity> result = teamRepository.findAll();
        assertEquals(3, result.size());
    }

    @Test
    void findByIdNotExists() {
        Optional<TeamEntity> result = teamRepository.findById(33333);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByCountryExists() {
        List<TeamEntity> result = teamRepository.findByCountry("France");
        assertEquals(1, result.size());
    }

    @Test
    void findByCountryNotExists() {
        List<TeamEntity> result = teamRepository.findByCountry("Ukraine");
        assertTrue(result.isEmpty());
    }

    @Test
    void findBySeriesContaining() {
        createTeam("Audi", "Germany", "Le Mans");

        List<TeamEntity> teams = teamRepository.findBySeriesContaining("Le Mans");
        assertEquals(3, teams.size());
    }

    @Test
    void findBySeriesContainingNotExist() {
        List<TeamEntity> teams = teamRepository.findBySeriesContaining("F2");
        assertTrue(teams.isEmpty());
    }


}