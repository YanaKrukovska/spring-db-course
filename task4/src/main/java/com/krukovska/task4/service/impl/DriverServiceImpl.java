package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;

    @Autowired
    public DriverServiceImpl(DriverRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Driver> findAllByTeamCountry(String country) {
        return repository.findAllByTeamCountry(country);
    }

    @Override
    public List<Driver> findAllFromSameCountryAsTeam(String country) {
        return repository.findAllByCountryAndTeamCountry(country, country);
    }

    @Override
    public List<Driver> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Driver findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    @Override
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Driver saveRequiresNew(Driver driver) {
        Driver savedUser = repository.save(driver);
        if ("Max Verstappen".equals(driver.getFullName())) {
            throw new RuntimeException("DummyException: this should cause rollback of second insert only!");
        }
        return savedUser;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Driver saveMandatory(Driver driver) {
        Driver savedUser = repository.save(driver);
        if ("Max Verstappen".equals(driver.getFullName())) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
        return savedUser;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Driver saveNotSupported(Driver driver) {
        Driver savedUser = repository.save(driver);
        if ("Max Verstappen".equals(driver.getFullName())) {
            throw new RuntimeException("DummyException: this should cause rollback of the first insert!");
        }
        return savedUser;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Driver saveSupports(Driver driver) {
        Driver savedUser = repository.save(driver);
        if ("Max Verstappen".equals(driver.getFullName())) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!!");
        }
        return savedUser;
    }
}
