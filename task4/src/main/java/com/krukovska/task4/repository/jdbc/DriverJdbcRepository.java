package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.mappers.DriverRowMapper;
import com.krukovska.task4.model.DriverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import static com.krukovska.task4.model.DriverEntity.TABLE_NAME;

@Repository
public class DriverJdbcRepository extends AbstractRepository<DriverEntity, DriverRowMapper> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DriverJdbcRepository(JdbcTemplate jdbcTemplate) {
        super(new DriverRowMapper(), jdbcTemplate, TABLE_NAME);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DriverEntity create(DriverEntity entity) {
        final String sql = "insert into driver(full_name,country,team_id) values(?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFullName());
            ps.setString(2, entity.getCountry());
            ps.setLong(3, entity.getTeam().getId());
            return ps;
        }, holder);

        long driverId = holder.getKey().longValue();
        entity.setId(driverId);
        return entity;
    }

}
