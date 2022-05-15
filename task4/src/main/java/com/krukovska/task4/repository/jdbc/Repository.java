package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {

    T create(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    void deleteAll();

    List<T> findByCountry(String country);
}
