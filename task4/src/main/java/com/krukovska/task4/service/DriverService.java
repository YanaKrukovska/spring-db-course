package com.krukovska.task4.service;

import com.krukovska.task4.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAllByTeamCountry(String country);

    List<Driver> findAllFromSameCountryAsTeam(String country);

    List<Driver> findAll();

    Driver save(Driver driver);

    void deleteAll();
}
