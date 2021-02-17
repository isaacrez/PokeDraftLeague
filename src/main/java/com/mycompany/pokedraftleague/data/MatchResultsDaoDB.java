/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static final class MatchResultsMapper implements RowMapper<MatchResults> {
        @Override
        public MatchResults mapRow(ResultSet rs, int index) throws SQLException {
            MatchResults results = new MatchResults();
            results.setMatchId(rs.getInt("matchId"));

            int differential = rs.getInt("SUM(directKOs)")
                    + rs.getInt("SUM(indirectKOs)") - rs.getInt("SUM(wasKOed)");
            results.setDifferential(differential);
            
            boolean wasWon = differential > 0;
            results.setWasWon(wasWon);

            return results;
        }
    }
}
