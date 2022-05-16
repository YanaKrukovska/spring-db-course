package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;
import com.krukovska.task4.service.DriverService;
import com.krukovska.task4.service.TeamManagementService;
import com.krukovska.task4.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@AllArgsConstructor
public class TeamManagementServiceImpl implements TeamManagementService {

    private final DriverService driverService;
    private final TeamService teamService;
    private final PlatformTransactionManager transactionManager;

    @Override
    public Team create(Team team, Driver driver) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        Team savedTeam = null;
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            savedTeam = teamService.save(team);
            driver.setTeam(savedTeam);
            driverService.save(driver);
            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
        }
        return savedTeam;
    }

    @Override
    @Transactional
    public Team createRequiresNew(Team team, Driver driver) {
        Team savedTeam = teamService.save(team);
        driver.setTeam(savedTeam);
        driverService.saveRequiresNew(driver);
        return savedTeam;
    }

    @Override
    @Transactional
    public Team createMandatory(Team team, Driver driver) {
        Team savedTeam = teamService.save(team);
        driver.setTeam(savedTeam);
        driverService.saveMandatory(driver);
        return savedTeam;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Team createNever(Team team) {
        return teamService.save(team);
    }

    @Override
    @Transactional
    public Team createNotSupported(Team team, Driver driver) {
        Team savedTeam = teamService.save(team);
        driver.setTeam(savedTeam);
        driverService.saveNotSupported(driver);
        return savedTeam;
    }

    @Override
    @Transactional
    public Team createSupports(Team team, Driver driver) {
        Team savedTeam = teamService.save(team);
        driver.setTeam(savedTeam);
        driverService.saveSupports(driver);
        return savedTeam;
    }
}
