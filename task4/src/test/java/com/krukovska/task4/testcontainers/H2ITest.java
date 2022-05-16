package com.krukovska.task4.testcontainers;

import com.krukovska.task4.H2BaseTest;
import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class H2ITest extends H2BaseTest {


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

}
