package com.krukovska.task4.service;

import com.krukovska.task4.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAllBySeriesContaining(String series);

    List<Team> findAll();

    Team findById(long id);

    Team save(Team team);

    void deleteAll();

    Team findByName(String name);
}
