package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.model.DriverEntity;
import com.krukovska.task4.model.TeamEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-h2.properties")
public class BaseJdbcTest {

    @Autowired
    protected DriverJdbcRepository driverRepository;

    @Autowired
    protected TeamJdbcRepository teamRepository;

    @BeforeEach
    public void initializeDb() {
        TeamEntity alphaTauri = createTeam("Alpha Tauri", "Italy", "F1");
        TeamEntity mcLaren = createTeam("McLaren", "UK", "F1,Le Mans");
        TeamEntity renault = createTeam("Renault", "France", "Le Mans,F1");
        createDriver("Pierre Gasly", "France", alphaTauri);
        createDriver("Daniel Ricciardo", "Australia", mcLaren);
        createDriver("Esteban Ocon", "France", renault);
        createDriver("Yuki Tsunoda", "Japan", alphaTauri);
    }

    @AfterEach
    public void clearDB() {
        driverRepository.deleteAll();
        teamRepository.deleteAll();
    }

    protected TeamEntity createTeam(String name, String country, String series) {
        return teamRepository.create(new TeamEntity(name, country, series));
    }

    protected DriverEntity createDriver(String fullName, String country, TeamEntity team) {
        return driverRepository.create(new DriverEntity(fullName, country, team));
    }
}
