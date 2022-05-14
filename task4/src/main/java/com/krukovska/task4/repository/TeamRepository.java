package com.krukovska.task4.repository;

import com.krukovska.task4.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
