/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;
import com.mycompany.pokedraftleague.models.Team;
import com.mycompany.pokedraftleague.models.TeamResults;
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
public class MatchResultsDaoDB implements MatchResultsDao {
    
    private final JdbcTemplate jdbc;
    private final TeamDao teamDao;
    
    @Autowired
    public MatchResultsDaoDB(JdbcTemplate jdbcTemplate, TeamDao teamDao) {
        this.jdbc = jdbcTemplate;
        this.teamDao = teamDao;
    }
    
    @Override
    public MatchResults getMatchResultsFor(int matchId, int teamId) {
        try {
            final String GET_STATS = "SELECT matchId, teamId, "
                    + "SUM(directKOs), SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchattendee "
                    + "WHERE matchId = ? AND teamId = ?";
            
            MatchResults results = jdbc.queryForObject(GET_STATS,
                    new MatchResultsMapper(),
                    matchId,
                    teamId);
            results.setTeam(teamDao.getTeamById(teamId));
            
            return results;
            
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<MatchResults> getTeamResults(int teamId, int leagueId) {
        try {
            final String GET_TEAM_STATS = "SELECT matchId, SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON m.id = ma.matchId "
                    + "WHERE ma.teamId = ? AND m.leagueId = ? "
                    + "GROUP BY m.id";
            List<MatchResults> results = jdbc.query(GET_TEAM_STATS,
                    new MatchResultsMapper(),
                    teamId,
                    leagueId);
            
            return results;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    public static final class MatchResultsMapper implements RowMapper<MatchResults> {
        @Override
        public MatchResults mapRow(ResultSet rs, int index) throws SQLException {
            MatchResults results = new MatchResults();
            results.setMatchId(rs.getInt("matchId"));

            int differential = rs.getInt("SUM(directKOs)")
                    + rs.getInt("SUM(indirectKOs)") - rs.getInt("SUM(wasKOed)");
            results.setDifferential(differential);
            
            boolean wasWon = differential > 0;
            results.setWon(wasWon);

            return results;
        }
    }
}
