package com.krukovska.task4.service;

import com.krukovska.task4.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAllByTeamCountry(String country);

    List<Driver> findAllFromSameCountryAsTeam(String country);

    List<Driver> findAll();

    Driver save(Driver driver);

    Driver saveRequiresNew(Driver driver);

    Driver saveMandatory(Driver driver);

    Driver saveNotSupported(Driver driver);

    void deleteAll();

    Driver findByFullName(String fullName);

    Driver saveSupports(Driver driver);
}
