package com.krukovska.task4.service;

import com.krukovska.task4.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(long id);
}
