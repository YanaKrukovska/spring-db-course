package com.krukovska.task4;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.service.DriverService;
import com.krukovska.task4.service.TeamService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-h2.properties")
public class H2BaseTest {

    @Autowired
    protected DriverService driverService;

    @Autowired
    protected TeamService teamService;

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

    protected Team createTeam(String name, String country, String series) {
        return teamService.save(new Team(name, country, series));
    }

    protected Driver createDriver(String fullName, String country, Team team) {
        return driverService.save(new Driver(fullName, country, team));
    }

}
