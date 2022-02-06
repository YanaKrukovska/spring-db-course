package com.krukovska.springdbcourse.controller;

import com.krukovska.springdbcourse.persistence.model.Group;
import com.krukovska.springdbcourse.persistence.model.Product;
import com.krukovska.springdbcourse.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<Group> getGroup(@PathVariable Long groupId) {
        Group group = service.getById(groupId);
        log.info("Found group {}", group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Group>> findAll() {
        List<Group> groups = service.findAll();
        log.info("Found groups, list size= {}", groups.size());
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable Long groupId) {
        boolean isDeleted = service.delete(groupId);
        log.info("Deleted group with id {}", groupId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}
