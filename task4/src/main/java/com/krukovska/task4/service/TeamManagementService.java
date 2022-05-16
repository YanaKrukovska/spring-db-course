package com.krukovska.task4.service;

import com.krukovska.task4.model.Driver;
import com.krukovska.task4.model.Team;

public interface TeamManagementService {

    Team create(Team team, Driver driver);

    Team createRequiresNew(Team team, Driver driver);

    Team createMandatory(Team team, Driver driver);

    Team createNever(Team team);

    Team createNotSupported(Team team, Driver driver);

    Team createSupports(Team team, Driver driver);
}
