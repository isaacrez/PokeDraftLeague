/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.data.CoachDaoDB.CoachMapper;
import com.mycompany.pokedraftleague.data.TeamDaoDB.TeamMapper;
import com.mycompany.pokedraftleague.models.Coach;
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
                + "WHERE mt.matchId = ?";
        List<Team> teams = jdbc.query(GET_TEAMS_FOR_MATCH, new TeamMapper(), id);
        addCoachToTeams(teams);
        return teams;
    }
    
    private void addCoachToTeams(List<Team> teams) {
        final String GET_COACH_FOR_TEAM = "SELECT c.* FROM coach AS c "
                + "JOIN team AS t ON t.coachId = c.id "
                + "WHERE t.id = ?";
        
        for (Team team : teams) {
            Coach coach = jdbc.queryForObject(GET_COACH_FOR_TEAM, new CoachMapper(), team.getId());
            team.setCoach(coach);
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
