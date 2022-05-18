package com.krukovska.task4.service;

import com.krukovska.task4.model.Driver;

import java.util.List;

public interface SalaryService {
    int getDriverSalary(String fullName, int isolationLevel);

    int getDoubledAllSalary(int isolationLevel);

    int getDriverSalary(String driver);

    Driver saveDriver(Driver driver, int isolation);

    int setDriverSalary(String fullName, int salary, int isolationLevel, boolean rollback);


}
