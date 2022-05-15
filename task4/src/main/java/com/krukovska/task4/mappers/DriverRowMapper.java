package com.krukovska.task4.mappers;

import com.krukovska.task4.model.DriverEntity;
import com.krukovska.task4.model.TeamEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverRowMapper implements RowMapper<DriverEntity> {

    @Override
    public DriverEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        DriverEntity driver = new DriverEntity();
        driver.setId(rs.getLong("id"));
        driver.setFullName(rs.getString("full_name"));
        driver.setCountry(rs.getString("country"));
        driver.setTeam(new TeamEntity(rs.getLong("team_id")));
        return driver;
    }
}
