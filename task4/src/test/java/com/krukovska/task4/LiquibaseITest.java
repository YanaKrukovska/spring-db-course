package com.krukovska.task4;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.service.DriverService;
import com.krukovska.task4.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application.properties")
class LiquibaseITest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private TeamService teamService;

    @Test
    void findAllDrivers() {
        List<Driver> drivers = driverService.findAll();
        assertEquals(4, drivers.size());
    }

    @Test
    void findAllTeams() {
        List<Team> teams = teamService.findAll();
        assertEquals(3, teams.size());
    }

    @Test
    void checkSeriesWereUpdated() {
        assertEquals("F1", teamService.findById(1).getSeries());
        assertEquals("F1", teamService.findById(2).getSeries());
        assertEquals("F1", teamService.findById(3).getSeries());
    }
}
