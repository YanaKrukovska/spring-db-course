package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.Group;

import java.util.List;

public interface GroupService {

    Group getById(long id);

    List<Group> findAll();

    boolean delete(long id);

}
