/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.data.TeamDaoDB.TeamMapper;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.MatchResults;
import com.mycompany.pokedraftleague.models.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class MatchDaoDB implements MatchDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public MatchDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Match> getAllMatches() {
        final String GET_ALL_MATCHES = "SELECT * FROM match";
        return jdbc.query(GET_ALL_MATCHES, new MatchMapper());
    }

    @Override
    public Match getMatchById(int id) {
        try {
            final String GET_MATCH_BY_ID = "SELECT * FROM match WHERE id = ?";
            return jdbc.queryForObject(GET_MATCH_BY_ID, new MatchMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Team> getTeamsInMatch(int id) {
        final String GET_TEAMS_FOR_MATCH = "SELECT t.* FROM team AS t "
                + "JOIN matchteam AS mt ON t.id = mt.teamId "
                + "JOIN match AS m ON mt.matchId = m.id "
                + "WHERE m.id = ?";
        return jdbc.query(GET_TEAMS_FOR_MATCH, new TeamMapper(), id);
    }
    
    @Override
    public MatchResults getMatchResultsFor(MatchResults matchResults) {
        try {
            final String GET_STATS = "SELECT matchId, teamId, "
                    + "SUM(directKOs), SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchattendee "
                    + "WHERE matchId = ? AND teamId = ?";
            
            return jdbc.queryForObject(GET_STATS,
                    new MatchResultsMapper(),
                    matchResults.getMatchId(),
                    matchResults.getTeamId());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Match addMatch(Match match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMatch(Match match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMatchById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class MatchResultsMapper implements RowMapper<MatchResults> {
        @Override
        public MatchResults mapRow(ResultSet rs, int index) throws SQLException {
            MatchResults results = new MatchResults();
            results.setMatchId(rs.getInt("matchId"));
            results.setTeamId(rs.getInt("teamId"));
            results.setDirectKOs(rs.getInt("SUM(directKOs)"));
            results.setIndirectKOs(rs.getInt("SUM(indirectKOs)"));
            results.setDeaths(rs.getInt("SUM(wasKOed)"));
            return results;
        }
    }
    
    public static final class MatchMapper implements RowMapper<Match> {
        @Override
        public Match mapRow(ResultSet rs, int index) throws SQLException {
            Match match = new Match();
            match.setId(rs.getInt("id"));
            match.setStatus(rs.getString("status"));
            match.setCompletedOn(rs.getDate("completedOn"));
            match.setScheduledWeek(rs.getInt("scheduledWeek"));
            return match;
        }
    }
}
