/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.league;

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
            final String GET_TEAM_RESULTS = "SELECT rs.teamId, "
                    + "COUNT(*) AS gamesPlayed, "
                    + "SUM(rs.won) AS gamesWon, "
                    + "SUM(rs.differential) AS differential "
                    + "FROM (SELECT matchId, teamId, "
                    + 	"NOT(SUM(wasKOed)) = 6 AS won, "
                    + 	"SUM(directKOs) + SUM(indirectKOs) - SUM(wasKOed) AS differential "
                    + 	"FROM matchattendee AS ma "
                    + 	"JOIN `match` AS m ON m.id = ma.matchId "
                    + 	"WHERE ma.teamId = ? AND m.leagueId = ? "
                    + 	"GROUP BY m.id) AS rs";
            
            TeamResults rs = jdbc.queryForObject(GET_TEAM_RESULTS,
                    new TeamResultsMapper(),
                    teamId,
                    leagueId);
            return rs;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }
    
    @Override
    public List<TeamResults> getTeamResultsForLeague(int leagueId) {
        final String GET_TEAM_RESULTS_FOR_LEAGUE = "SELECT t.*, "
                +   "COUNT(rs.won) AS gamesPlayed, "
                +   "IFNULL(SUM(rs.won), 0) AS gamesWon, "
                +   "IFNULL(SUM(rs.differential), 0) AS differential "
                + "FROM team AS t "
                + "JOIN leagueTeam AS lt ON t.id = lt.teamId "
                + "LEFT OUTER JOIN (SELECT "
                +       "matchId, "
                +       "teamId,  "
                +       "NOT(SUM(wasKOed)) = 6 AS won,  "
                +       "SUM(directKOs) + SUM(indirectKOs) - SUM(wasKOed) AS differential "
                +   "FROM matchattendee AS ma "
                +   "JOIN `match` AS m ON m.id = ma.matchId  "
                +   "WHERE m.leagueId = ? "
                +   "GROUP BY teamId, m.id) "
                + "AS rs ON rs.teamId = t.id "
                + "WHERE lt.leagueId = ? "
                + "GROUP BY t.id";
        List<TeamResults> results = jdbc.query(GET_TEAM_RESULTS_FOR_LEAGUE,
                new TeamResultsMapper(),
                leagueId,
                leagueId);
        return results;
    }
    
    public static final class TeamResultsMapper implements RowMapper<TeamResults> {
        
        @Override
        public TeamResults mapRow(ResultSet rs, int i) throws SQLException {
            TeamResults teamResults = new TeamResults();
            teamResults.setGamesPlayed(rs.getInt("gamesPlayed"));
            teamResults.setGamesWon(rs.getInt("gamesWon"));
            teamResults.setDifferential(rs.getInt("differential"));
            
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            team.setAcronym(rs.getString("acronym"));
            teamResults.setTeam(team);
            return teamResults;
        }
        
    }
}
