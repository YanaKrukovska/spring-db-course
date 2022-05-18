package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static java.lang.Thread.sleep;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final DriverRepository repository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public SalaryServiceImpl(DriverRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }


    @Override
    public int getDriverSalary(String fullName, int isolationLevel) {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(isolationLevel);


        TransactionStatus status = transactionManager.getTransaction(definition);

        Driver driver = repository.findByFullName(fullName);


        transactionManager.commit(status);
        return driver.getSalary();
    }

    @Override
    public int getDoubledAllSalary(int isolationLevel) {
        

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(isolationLevel);


        TransactionStatus status = transactionManager.getTransaction(definition);

        int firstSum = repository.findAll().stream().map(Driver::getSalary).reduce(0, Integer::sum);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int secondSum = repository.findAll().stream().map(Driver::getSalary).reduce(0, Integer::sum);

        transactionManager.commit(status);

        return firstSum + secondSum;
    }

    @Override
    public int getDriverSalary(String driver) {
        return repository.findByFullName(driver).getSalary();
    }


    @Override
    public Driver saveDriver(Driver driver, int isolation) {

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(isolation);


        TransactionStatus status = transactionManager.getTransaction(definition);

        Driver savedUser = repository.save(driver);;

        transactionManager.commit(status);


        return savedUser;
    }

    @Override
    public int setDriverSalary(String fullName, int salary, int t, boolean rollback) {

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(t);


        TransactionStatus status = transactionManager.getTransaction(definition);

        Driver driver = repository.findByFullName(fullName);
        driver.setSalary(salary);
        repository.save(driver);
        status.flush();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (rollback) {
            transactionManager.rollback(status);
        } else {
            transactionManager.commit(status);
        }

        return salary;
    }

    @Override
    public boolean deleteDriver(String driverName, int isolation) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(isolation);


        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repository.deleteByFullName(driverName);
        transactionManager.commit(status);

        return true;
    }

    @Override
    public List<Driver> getDrivers() {
        return repository.findAll();
    }
}
