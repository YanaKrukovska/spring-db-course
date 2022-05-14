package com.krukovska.task4.service.impl;

import com.krukovska.task4.model.Team;
import com.krukovska.task4.repository.TeamRepository;
import com.krukovska.task4.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Team> findAllBySeriesContaining(String series) {
        return repository.findAllBySeriesContaining(series);
    }

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public Team findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Team save(Team team) {
        return repository.save(team);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Team findByName(String name) {
        return repository.findByName(name);
    }
}
