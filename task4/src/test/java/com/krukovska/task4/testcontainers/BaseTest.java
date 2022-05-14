package com.krukovska.task4.testcontainers;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.service.DriverService;
import com.krukovska.task4.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.SocketUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.Base58;

@SpringBootTest
@ContextConfiguration(initializers = {BaseTest.Initializer.class})
public class BaseTest {

    @Autowired
    protected DriverService driverService;

    @Autowired
    protected TeamService teamService;

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13.4")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("testdb")
            .withNetworkAliases("postgres-" + Base58.randomString(6));

    static {
        POSTGRES.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + POSTGRES.getJdbcUrl(),
                    "spring.datasource.username=" + POSTGRES.getUsername(),
                    "spring.datasource.password=" + POSTGRES.getPassword(),
                    "local.server.port=" + SocketUtils.findAvailableTcpPort()
            )
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    protected Team createTeam(String name, String country, String series) {
        return teamService.save(new Team(name, country, series));
    }

    protected Driver createDriver(String fullName, String country, Team team) {
        return driverService.save(new Driver(fullName, country, team));
    }

}

