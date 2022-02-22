package com.krukovska.task1.controller;

import com.krukovska.task1.persistence.model.Storage;
import com.krukovska.task1.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private final StorageService service;

    @Autowired
    public StorageController(StorageService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Storage>> findAll() {
        List<Storage> result = service.findAll();
        log.info("Found storages, list size= {}", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
