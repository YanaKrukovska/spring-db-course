package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.repository.TeamRepository;
import com.krukovska.task4.service.TeamManagementService;
import lombok.SneakyThrows;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-h2.properties")
class TeamManagementServiceImplTest {

    @Autowired
    private TeamManagementService service;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DriverRepository driverRepository;

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
        driverRepository.deleteAll();
        teamRepository.deleteAll();
    }

    // Required + Required

    @Test
    void createTeamAndDriverImperativeSuccess() {
        service.create(new Team("Ferrari", "Italy", "F1"),
                new Driver("Charles Leclerc", "Monaco", null));

        assertNotNull(teamRepository.findByName("Ferrari"));
        assertNotNull(driverRepository.findByFullName("Charles Leclerc"));
    }

    @Test
    void createTeamAndDriverImperativeFail() {
        service.create(new Team("Alpha Tauri", "Italy", "F1"),
                new Driver("Charles Leclerc", "Monaco", null));

        assertNotNull(teamRepository.findByName("Alpha Tauri"));
        assertNull(driverRepository.findByFullName("Charles Leclerc"));
    }

    // Required + Requires New
    @Ignore
    @Test
    void createTeamRequireSuccess() {
        try {
            service.createRequiresNew(new Team("Ferrari", "Italy", "F1"),
                    new Driver("Sergio Perez", "Mexico", new Team("Ferrari", "Italy", "F1")));
        } catch (RuntimeException e) {
        }

        assertNotNull(driverRepository.findByFullName("Sergio Perez"));
    }

    @Test
    @SneakyThrows
    void createTeamRequiresNewFail() {
        try {
            service.createRequiresNew(new Team("Red Bull", "UK", "F1"),
                    new Driver("Max Verstappen", "Netherlands", null));
        } catch (RuntimeException e) {
        }

        assertNull(teamRepository.findByName("Red Bull"));
        assertNull(driverRepository.findByFullName("Max Verstappen"));
    }

    // Required + Mandatory
    @Test
    void createTeamMandatorySuccess() {
        try {
            service.createMandatory(new Team("Red Bull", "UK", "F1"),
                    new Driver("Sergio Perez", "Mexico", null));
        } catch (RuntimeException e) {
        }

        assertNotNull(teamRepository.findByName("Red Bull"));
        assertNotNull(driverRepository.findByFullName("Sergio Perez"));
    }

    @Test
    void createTeamMandatoryTransactionsFail() {
        try {
            service.createMandatory(new Team("Red Bull", "UK", "F1"),
                    new Driver("Max Verstappen", "Netherlands", null));
        } catch (RuntimeException e) {
        }

        assertNull(teamRepository.findByName("Red Bull"));
        assertNull(driverRepository.findByFullName("Max Verstappen"));
    }

    // Required + Never
    @Test
    void createTeamNeverSuccess() {
        service.createNever(new Team("Red Bull", "UK", "F1"));
        assertNotNull(teamRepository.findByName("Red Bull"));
    }

    // Required + Not Supported
    @Test
    void createTeamNotSupportedTransactionsFail() {
        try {
            service.createNotSupported(new Team("Red Bull", "UK", "F1"),
                    new Driver("Max Verstappen", "Netherlands", null));
        } catch (RuntimeException e) {
        }

        assertNull(teamRepository.findByName("Red Bull"));
        assertNull(driverRepository.findByFullName("Max Verstappen"));
    }

    // Required + Supports
    @Test
    void createTeamSupportsSuccess() {
        try {
            service.createSupports(new Team("Red Bull", "UK", "F1"),
                    new Driver("Sergio Perez", "Mexico", null));
        } catch (RuntimeException e) {
        }

        assertNotNull(teamRepository.findByName("Red Bull"));
        assertNotNull(driverRepository.findByFullName("Sergio Perez"));
    }

    @Test
    void createTeamSupportsFail() {
        try {
            service.createSupports(new Team("Red Bull", "UK", "F1"),
                    new Driver("Max Verstappen", "Netherlands", null));
        } catch (RuntimeException e) {
        }

        assertNull(teamRepository.findByName("Red Bull"));
        assertNull(driverRepository.findByFullName("Max Verstappen"));
    }

    private Team createTeam(String name, String country, String series) {
        return teamRepository.save(new Team(name, country, series));
    }

    private Driver createDriver(String fullName, String country, Team team) {
        return driverRepository.save(new Driver(fullName, country, team));
    }

}