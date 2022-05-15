package com.krukovska.task4.mappers;

import com.krukovska.task4.model.TeamEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamRowMapper implements RowMapper<TeamEntity> {

    @Override
    public TeamEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeamEntity team = new TeamEntity();
        team.setId(rs.getLong("id"));
        team.setName(rs.getString("name"));
        team.setCountry(rs.getString("country"));
        team.setSeries(rs.getString("series"));
        return team;
    }
}
