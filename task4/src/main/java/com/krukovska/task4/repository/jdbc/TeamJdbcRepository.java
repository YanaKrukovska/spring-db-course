package com.krukovska.task4.repository.jdbc;

import com.krukovska.task4.mappers.TeamRowMapper;
import com.krukovska.task4.model.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static com.krukovska.task4.model.TeamEntity.TABLE_NAME;

@Repository
public class TeamJdbcRepository extends AbstractRepository<TeamEntity, TeamRowMapper> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamJdbcRepository(JdbcTemplate jdbcTemplate) {
        super(new TeamRowMapper(), jdbcTemplate, TABLE_NAME);
        this.jdbcTemplate = jdbcTemplate;
    }

    public TeamEntity create(TeamEntity team) {
        final String sql = "insert into team(name, country, series) values(?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getName());
            ps.setString(2, team.getCountry());
            ps.setString(3, team.getSeries());
            return ps;
        }, holder);

        long teamId = (Long) holder.getKeyList().get(0).get("id");
        team.setId(teamId);
        return team;
    }

    public List<TeamEntity> findBySeriesContaining(String series) {
        String selectQuery = "select * from " + TABLE_NAME + " where series like ?";
        String param = "%" + series + "%";
        return jdbcTemplate.query(selectQuery, mapper, param);
    }
}
