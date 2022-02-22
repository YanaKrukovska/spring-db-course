package com.krukovska.task1.service.impl;

import com.krukovska.task1.persistence.model.Group;
import com.krukovska.task1.persistence.repository.GroupRepository;
import com.krukovska.task1.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Autowired
    public GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Group getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
