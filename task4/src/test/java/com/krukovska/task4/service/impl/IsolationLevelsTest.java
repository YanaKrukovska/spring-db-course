package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.repository.TeamRepository;
import com.krukovska.task4.service.SalaryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.transaction.TransactionDefinition.*;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-h2.properties")
class IsolationLevelsTest {

    public static final String DRIVER = "Yuki Tsunoda";

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DriverRepository driverRepository;


    @Nested
    @DisplayName("Tests for phantom read issue ")
    class PhantomReadTest {
        @Test
        void phantomReadDemo_ISOLATION_READ_COMMITTED() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> {
                Driver phantomDriver = createDriver("FF", "Australia", teamRepository.findByName("Renault"), 500);
                salaryService.saveDriver(phantomDriver, ISOLATION_READ_COMMITTED);

            });
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_READ_COMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(5000);
            // 8500 bc phantom driver is included
            assertEquals(8500, doubledTotalSalary.get());
        }


        @Test
        void phantomReadDemo_ISOLATION_READ_UNCOMMITTED() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> {
                Driver phantomDriver = createDriver("FF", "Australia", teamRepository.findByName("Renault"), 500);
                salaryService.saveDriver(phantomDriver, ISOLATION_READ_UNCOMMITTED);

            });
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_READ_UNCOMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(5000);
            // 8500 bc phantom driver is included
            assertEquals(8500, doubledTotalSalary.get());
        }

        @Test
        void phantomReadDemo_ISOLATION_SERIALIZABLE() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> {
                Driver phantomDriver = createDriver("FF", "Australia", teamRepository.findByName("Renault"), 500);
                salaryService.saveDriver(phantomDriver, ISOLATION_SERIALIZABLE);

            });
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_SERIALIZABLE)));

            t1.start();
            t2.start();

            Thread.sleep(5000);
            // 8000 bc phantom driver is NOT included
            assertEquals(8000, doubledTotalSalary.get());
        }

    }


    @Nested
    @DisplayName("Tests for dirty read issue ")
    class DirtyReadTest {
        @Test
        void dirtyReadDemo_ISOLATION_READ_UNCOMMITTED() throws InterruptedException {
            Map<String, Integer> dataAccum = new HashMap<>();
            dataAccum.put("Before", salaryService.getDriverSalary(DRIVER, ISOLATION_READ_UNCOMMITTED));

            Thread t1 = new Thread(() -> salaryService.setDriverSalary(DRIVER, 2000, ISOLATION_READ_UNCOMMITTED, true));

            Thread t2 = new Thread(() -> dataAccum.put("Middle", salaryService.getDriverSalary(DRIVER, ISOLATION_READ_UNCOMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(3000);
            dataAccum.put("After", salaryService.getDriverSalary(DRIVER));

            assertEquals(1000, dataAccum.get("Before"));
            // uncommitted - dirty read is presented
            assertEquals(2000, dataAccum.get("Middle"));
            //commit rollback
            assertEquals(1000, dataAccum.get("After"));
        }

        @Test
        void dirtyReadDemo_ISOLATION_READ_COMMITTED() throws InterruptedException {
            Map<String, Integer> dataAccum = new HashMap<>();
            dataAccum.put("Before", salaryService.getDriverSalary(DRIVER, ISOLATION_READ_COMMITTED));


            Thread t1 = new Thread(() -> salaryService.setDriverSalary(DRIVER, 2000, ISOLATION_READ_COMMITTED, true));

            Thread t2 = new Thread(() -> dataAccum.put("Middle", salaryService.getDriverSalary(DRIVER, ISOLATION_READ_COMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(3000);
            dataAccum.put("After", salaryService.getDriverSalary(DRIVER));

            assertEquals(1000, dataAccum.get("Before"));
            // uncommitted - dirty read is NOT presented
            assertEquals(1000, dataAccum.get("Middle"));
            //commit rollback
            assertEquals(1000, dataAccum.get("After"));
        }

        @Test
        void dirtyReadDemo_ISOLATION_SERIALIZABLE() throws InterruptedException {
            Map<String, Integer> dataAccum = new HashMap<>();
            dataAccum.put("Before", salaryService.getDriverSalary(DRIVER, ISOLATION_SERIALIZABLE));


            Thread t1 = new Thread(() -> salaryService.setDriverSalary(DRIVER, 2000, ISOLATION_SERIALIZABLE, true));

            Thread t2 = new Thread(() -> dataAccum.put("Middle", salaryService.getDriverSalary(DRIVER, ISOLATION_SERIALIZABLE)));
            t1.start();
            t2.start();

            Thread.sleep(3000);
            dataAccum.put("After", salaryService.getDriverSalary(DRIVER));

            assertEquals(1000, dataAccum.get("Before"));
            // uncommitted - dirty read is NOT presented
            assertEquals(1000, dataAccum.get("Middle"));
            //commit rollback
            assertEquals(1000, dataAccum.get("After"));
        }
    }

    @Disabled
    @Nested
    @DisplayName("Tests for unrepeatable read issue ")
    class UnrepeatableReadTest {
        @Test
        void unrepeatableReadDemo_ISOLATION_READ_COMMITTED() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Driver driver = driverRepository.findByFullName(DRIVER);
                driver.setSalary(1500);
                driverRepository.save(driver);
                System.out.println("Salary updated");
            }
            );
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_READ_COMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(5000);
            // 4000 + 4000  != 4000 + 4500
            Integer test = driverRepository.findAll().stream().map(Driver::getSalary).reduce(0, Integer::sum);
            System.out.println("final salary value" + test);
            assertEquals(8500, doubledTotalSalary.get());
        }


        @Test
        void unrepeatableReadDemo_ISOLATION_READ_UNCOMMITTED() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> salaryService.setDriverSalary(DRIVER, 1500, ISOLATION_READ_COMMITTED, false));
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_READ_COMMITTED)));

            t1.start();
            t2.start();

            Thread.sleep(4000);
            // 4000 + 4000  != 4000 + 4500
            Integer test = driverRepository.findAll().stream().map(Driver::getSalary).reduce(0, Integer::sum);

            assertEquals(8500, doubledTotalSalary.get());
            assertEquals(4500, test);
        }

        @Test
        void unrepeatableReadDemo_ISOLATION_SERIALIZABLE() throws InterruptedException {

            AtomicInteger doubledTotalSalary = new AtomicInteger(0);

            Thread t1 = new Thread(() -> {
                Driver phantomDriver = createDriver("FF", "Australia", teamRepository.findByName("Renault"), 500);
                salaryService.saveDriver(phantomDriver, ISOLATION_SERIALIZABLE);

            });
            Thread t2 = new Thread(() -> doubledTotalSalary.set(salaryService.getDoubledAllSalary(ISOLATION_SERIALIZABLE)));

            t1.start();
            t2.start();

            Thread.sleep(3000);
            // 8000 bc phantom driver is NOT included
            assertEquals(8000, doubledTotalSalary.get());
        }
    }

    @BeforeEach
    public void initializeDb() {
        Team alphaTauri = createTeam("Alpha Tauri", "Italy", "F1");
        Team mcLaren = createTeam("McLaren", "UK", "F1,Le Mans");
        Team renault = createTeam("Renault", "France", "Le Mans,F1");
        createDriver("Pierre Gasly", "France", alphaTauri, 1000);
        createDriver("Daniel Ricciardo", "Australia", mcLaren, 1000);
        createDriver("Esteban Ocon", "France", renault, 1000);
        createDriver(DRIVER, "Japan", alphaTauri, 1000);
    }

    @AfterEach
    public void clearDB() {
        driverRepository.deleteAll();
        teamRepository.deleteAll();
    }


    private Team createTeam(String name, String country, String series) {
        return teamRepository.save(new Team(name, country, series));
    }

    private Driver createDriver(String fullName, String country, Team team, int salary) {
        return driverRepository.save(new Driver(fullName, country, team, salary));
    }

}