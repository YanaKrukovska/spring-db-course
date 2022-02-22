package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findById(long id);
}
