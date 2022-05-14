package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.repository.DriverRepository;
import com.krukovska.task4.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
