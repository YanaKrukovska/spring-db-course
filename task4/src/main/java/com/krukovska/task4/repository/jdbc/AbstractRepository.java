package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.model.Entity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity, M extends RowMapper<T>> implements Repository<T> {

    protected final M mapper;
    private final JdbcTemplate jdbcTemplate;
    private final String tableName;

    protected AbstractRepository(M mapper, JdbcTemplate jdbcTemplate, String tableName) {
        this.mapper = mapper;
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
    }

    @Override
    public Optional<T> findById(long id) {
        String selectQuery = "select * from " + tableName + " where id=?";
        List<T> entities = jdbcTemplate.query(selectQuery, mapper, id);
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entities.get(0));
    }

    @Override
    public List<T> findAll() {
        String selectQuery = "select * from " + tableName;
        return jdbcTemplate.query(selectQuery, mapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from " + tableName);
    }

    @Override
    public List<T> findByCountry(String country) {
        String selectQuery = "select * from " + tableName + " where country=?";
        return jdbcTemplate.query(selectQuery, mapper, country);
    }
}
