/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.data.TeamDaoDB.TeamMapper;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.Team;
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
public class MatchDaoDB implements MatchDao {
    
    @Autowired
    private final TeamDao teamDao;
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public MatchDaoDB(JdbcTemplate jdbcTemplate, TeamDao teamDao) {
        this.jdbc = jdbcTemplate;
        this.teamDao = teamDao;
    }

    @Override
    public List<Match> getAllMatches() {
        final String GET_ALL_MATCHES = "SELECT * FROM match";
        List<Match> matches = jdbc.query(GET_ALL_MATCHES, new MatchMapper());
        addTeamsToMatch(matches);
        return matches;
    }

    @Override
    public Match getMatchById(int id) {
        try {
            final String GET_MATCH_BY_ID = "SELECT * FROM match WHERE id = ?";
            Match match = jdbc.queryForObject(GET_MATCH_BY_ID, new MatchMapper(), id);
            addTeamsToMatch(match);
            return match;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Match> getMatchesByLeagueId(int id) {
        final String GET_MATCH_BY_ID = "SELECT * FROM `match` WHERE leagueId = ?";
        List<Match> matches = jdbc.query(GET_MATCH_BY_ID, new MatchMapper(), id);
        addTeamsToMatch(matches);
        return matches;
    }
    
    private void addTeamsToMatch(List<Match> matches) {
        for (Match match : matches) {
            addTeamsToMatch(match);
        }
    }
    
    private void addTeamsToMatch(Match match) {
        match.setTeams(teamDao.getTeamsByMatchId(match.getId()));
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
    
    public static final class MatchMapper implements RowMapper<Match> {
        @Override
        public Match mapRow(ResultSet rs, int index) throws SQLException {
            Match match = new Match();
            match.setId(rs.getInt("id"));
            match.setDateSubmitted(rs.getDate("dateSubmitted"));
            match.setScheduledWeek(rs.getInt("scheduledWeek"));
            return match;
        }
    }
}
