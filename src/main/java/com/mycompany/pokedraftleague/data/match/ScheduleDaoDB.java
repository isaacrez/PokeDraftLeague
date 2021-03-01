/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.match;

import com.mycompany.pokedraftleague.data.league.TeamDaoDB.TeamMapper;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.MinimumMatch;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author isaacrez
 */
@Repository
public class ScheduleDaoDB implements ScheduleDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public ScheduleDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }
    
    @Override
    public MinimumMatch getMatchById(int id) {
        try {
            final String GET_MATCH_BY_ID = "SELECT * FROM `match` WHERE id = ?";
            MinimumMatch match = jdbc.queryForObject(GET_MATCH_BY_ID,
                    new MinimumMatchMapper(),
                    id);
            addAdvancedProperties(match);
            return match;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<MinimumMatch> getScheduleForLeague(int id) {
        final String GET_MATCH_BY_ID = "SELECT * FROM `match` WHERE leagueId = ?";
        List<MinimumMatch> matches = jdbc.query(GET_MATCH_BY_ID,
                new MinimumMatchMapper(),
                id);
        addAdvancedProperties(matches);
        return matches;
    }
    
    private void addAdvancedProperties(List<MinimumMatch> matches) {
        for (MinimumMatch match : matches) {
            addAdvancedProperties(match);
        }
    }
    
    private void addAdvancedProperties(MinimumMatch match) {
        addStatusToMatch(match);
        addTeamsToMatch(match);
    }
    
    private void addStatusToMatch(MinimumMatch match) {
        final String GET_MATCH_STATUS = "SELECT ms.label FROM matchStatus AS ms "
                + "JOIN `match` AS m ON m.statusId = ms.id "
                + "WHERE m.id = ?";
        match.setStatus(jdbc.queryForObject(GET_MATCH_STATUS, String.class, match.getId()));
    }
    
    private void addTeamsToMatch(MinimumMatch match) {
        final String GET_TEAMS_FOR_MATCH = "SELECT t.* FROM `match` AS m "
                + "JOIN matchTeam AS mt ON m.id = mt.matchId "
                + "JOIN team AS t on mt.teamId = t.id "
                + "WHERE m.id = ?";
        match.setTeams(jdbc.query(GET_TEAMS_FOR_MATCH,
                new TeamMapper(),
                match.getId()));
    }

    public static final class MinimumMatchMapper implements RowMapper<MinimumMatch> {
        @Override
        public MinimumMatch mapRow(ResultSet rs, int index) throws SQLException {
            MinimumMatch match = new MinimumMatch();
            match.setId(rs.getInt("id"));
            match.setDateSubmitted(rs.getDate("dateSubmitted"));
            match.setScheduledWeek(rs.getInt("scheduledWeek"));
            return match;
        }
    }
}
