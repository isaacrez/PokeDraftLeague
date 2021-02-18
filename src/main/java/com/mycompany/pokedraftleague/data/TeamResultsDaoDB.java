/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;
import com.mycompany.pokedraftleague.models.TeamResults;
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
public class TeamResultsDaoDB implements TeamResultsDao {

    private final JdbcTemplate jdbc;
    private final TeamDao teamDao;
    
    @Autowired
    TeamResultsDaoDB(JdbcTemplate jdbc, TeamDao teamDao) {
        this.jdbc = jdbc;
        this.teamDao = teamDao;
    }
    
    @Override
    public TeamResults getTeamResultsFor(int teamId, int leagueId) {
        try {
            final String GET_TEAM_RESULTS = "SELECT COUNT(*) AS gamesPlayed, " +
                    "SUM(rs.won) AS gamesWon, " +
                    "SUM(rs.differential) AS differential " +
                    "FROM (SELECT matchId, teamId, " +
                    "   NOT(SUM(wasKOed)) = 6 AS won, " +
                    "   SUM(directKOs) + SUM(indirectKOs) - SUM(wasKOed) AS differential " +
                    "   FROM matchattendee AS ma " +
                    "   JOIN `match` AS m ON m.id = ma.matchId " +
                    "   WHERE ma.teamId = ? AND m.leagueId = ? " +
                    "   GROUP BY m.id) AS rs";
            
            TeamResults rs = jdbc.queryForObject(GET_TEAM_RESULTS,
                    new TeamResultsMapper(),
                    teamId,
                    leagueId);
            rs.setTeam(teamDao.getTeamById(teamId));
            return rs;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    public static final class TeamResultsMapper implements RowMapper<TeamResults> {

        @Override
        public TeamResults mapRow(ResultSet rs, int i) throws SQLException {
            TeamResults teamResults = new TeamResults();
            teamResults.setGamesPlayed(rs.getInt("gamesPlayed"));
            teamResults.setGamesWon(rs.getInt("gamesWon"));
            teamResults.setDifferential(rs.getInt("differential"));
            return teamResults;
        }
        
    }
}
