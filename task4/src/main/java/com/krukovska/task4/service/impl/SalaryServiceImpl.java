package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
        System.out.println("First salary read:" + firstSum);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int secondSum = repository.findAll().stream().map(Driver::getSalary).reduce(0, Integer::sum);
        System.out.println("Second salary read:" + secondSum);
        transactionManager.commit(status);

        return firstSum + secondSum;
    }

    @Override
    public int getDoubledAllSalary(String fullName, int isolationLevel) {


        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(isolationLevel);


        TransactionStatus status = transactionManager.getTransaction(definition);

        int firstSum = repository.findByFullName(fullName).getSalary();
        System.out.println("First salary read:" + firstSum);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int secondSum = repository.findByFullName(fullName).getSalary();
        System.out.println("Second salary read:" + secondSum);
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
        Driver savedUser = repository.save(driver);
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

        if (rollback) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            transactionManager.rollback(status);
        } else {
            transactionManager.commit(status);
            System.out.println("Driver salary updated:" + salary);
        }

        return salary;
    }

}
