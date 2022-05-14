package com.krukovska.task4.testcontainers;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(value = {"classpath:application-testcontainers.properties"})
class ContainersITest extends BaseTest {

    @BeforeEach
    public void initializeDb() {
        Team alphaTauri = createTeam("Alpha Tauri", "Italy", "F1");
        Team mcLaren = createTeam("McLaren", "UK", "F1,Le Mans");
        Team renault = createTeam("Renault", "France", "Le Mans,F1");
        createDriver("Pierre Gasly", "France", alphaTauri);
        createDriver("Daniel Ricciardo", "Australia", mcLaren);
        createDriver("Esteban Ocon", "France", renault);
        createDriver("Yuki Tsunoda", "Japan", alphaTauri);
    }

    @AfterEach
    public void clearDB() {
        driverService.deleteAll();
        teamService.deleteAll();
    }

    @Test
    void findAllTeamThatParticipateInSeries() {
        createTeam("Audi", "Germany", "Le Mans");

        List<Team> teams = teamService.findAllBySeriesContaining("Le Mans");
        assertEquals(3, teams.size());
    }

    @Test
    void findAllDriversByTeamsCountry() {
        List<Driver> drivers = driverService.findAllByTeamCountry("Italy");
        assertEquals(2, drivers.size());
    }

    @Test
    void findAllDriversByNonExistingTeamCountry() {
        List<Driver> drivers = driverService.findAllByTeamCountry("Ukraine");
        assertEquals(0, drivers.size());
    }

    @Test
    void findDriversInLocalTeams() {
        Team mercedes = createTeam("Mercedes", "Germany", "F1");
        createDriver("Lewis Hamilton", "UK", mercedes);
        createDriver("Michael Schumacher", "Germany", mercedes);

        assertEquals(1, driverService.findAllFromSameCountryAsTeam("France").size());
        assertEquals(1, driverService.findAllFromSameCountryAsTeam("Germany").size());
    }


    @Test
    void findAllTeamsSuccess() {
        List<Team> teams = teamService.findAll();
        assertEquals(3, teams.size());
    }

    @Test
    void findAllDriversSuccess() {
        List<Driver> drivers = driverService.findAll();
        assertEquals(4, drivers.size());
    }
}
