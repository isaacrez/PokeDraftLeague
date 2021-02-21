/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.League;
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
public class LeagueDaoDB implements LeagueDao {
    
    private final JdbcTemplate jdbc;
    private final TeamDao teamDao;
    
    @Autowired
    public LeagueDaoDB(JdbcTemplate jdbcTemplate, TeamDao teamDao) {
        this.jdbc = jdbcTemplate;
        this.teamDao = teamDao;
    }

    @Override
    public List<League> getAllLeagues() {
        final String GET_ALL_LEAGUES = "SELECT * FROM league";
        List<League> leagues = jdbc.query(GET_ALL_LEAGUES, new LeagueMapper());
        addTeamsToLeague(leagues);
        return leagues;
    }

    @Override
    public League getLeagueById(int id) {
        try {
            final String GET_LEAGUE_BY_ID = "SELECT * FROM league WHERE id = ?";
            League league = jdbc.queryForObject(GET_LEAGUE_BY_ID, new LeagueMapper(), id); 
            addTeamsToLeague(league);
            return league;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    private void addTeamsToLeague(List<League> leagues) {
        for (League league : leagues) {
            addTeamsToLeague(league);
        }
    }
    
    private void addTeamsToLeague(League league) {
        league.setTeams(teamDao.getAllTeamsForLeague(league.getId()));
    }

    @Override
    public League addLeague(League league) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLeague(League league) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLeagueById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class LeagueMapper implements RowMapper<League> {
        @Override
        public League mapRow(ResultSet rs, int index) throws SQLException {
            League league = new League();
            league.setId(rs.getInt("id"));
            league.setName(rs.getString("name"));
            league.setAdmin(rs.getString("admin"));
            return league;
        }
    }
    
}
