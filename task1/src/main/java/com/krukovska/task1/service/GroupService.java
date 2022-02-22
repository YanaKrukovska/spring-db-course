package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.Group;

import java.util.List;

public interface GroupService {

    Group getById(long id);

    List<Group> findAll();

    boolean delete(long id);

}
