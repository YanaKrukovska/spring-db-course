package com.krukovska.task4.repository;

import com.krukovska.task4.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);

    List<Team> findAllBySeriesContaining(String series);
}
