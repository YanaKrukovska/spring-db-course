package com.krukovska.task4.repository;

import com.krukovska.task4.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByFullName(String fullName);

    List<Driver> findAllByTeamCountry(String country);

    List<Driver> findAllByCountryAndTeamCountry(String userCountry, String teamCountry);

    boolean deleteByFullName(String driverName);
}
