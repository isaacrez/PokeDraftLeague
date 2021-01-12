/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.data.PokemonDaoDB.PokemonMapper;
import com.mycompany.pokedraftleague.models.Lineup;
import com.mycompany.pokedraftleague.models.Pokemon;
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
public class TeamDaoDB implements TeamDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public TeamDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Team> getAllTeams() {
        final String GET_ALL_TEAMS = "SELECT * FROM team";
        return jdbc.query(GET_ALL_TEAMS, new TeamMapper());
    }

    @Override
    public List<Team> getAllTeamsForLeague(int leagueId) {
        final String GET_TEAM_BY_LEAGUE_ID = "SELECT t.* FROM team AS t "
                + "JOIN leagueteam AS lt ON t.id = lt.teamId "
                + "WHERE lt.leagueId = ?";
        return jdbc.query(GET_TEAM_BY_LEAGUE_ID, new TeamMapper(), leagueId);
    }

    @Override
    public Lineup getRosterById(int id) {
        final String GET_POKEMON_BY_TEAM_ID = "SELECT p.* FROM team AS t "
                + "JOIN roster AS r ON t.id = r.teamId "
                + "JOIN pokemon AS p ON r.pokeId = p.id "
                + "WHERE t.id = ?";
        List<Pokemon> pokemon = jdbc.query(GET_POKEMON_BY_TEAM_ID, new PokemonMapper(), id);
        return new Lineup(pokemon);
    }

    @Override
    public Team getTeamById(int id) {
        try {
            final String GET_TEAM_BY_ID = "SELECT * FROM team WHERE id = ?";
            return jdbc.queryForObject(GET_TEAM_BY_ID, new TeamMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Team addTeam(Team team) {
        final String ADD_TEAM = "INSERT INTO team (name, acronym) "
                + "VALUES (?, ?)";
        jdbc.update(ADD_TEAM,
                team.getName(),
                team.getAcronym());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        team.setId(newId);
        
        return team;
    }

    @Override
    public void updateTeam(Team team) {
        final String UPDATE_TEAM = "UPDATE team "
                + "SET name = ?, acronym = ?, coachId = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_TEAM,
                team.getName(),
                team.getAcronym(),
                team.getCoach().getId(),
                team.getId());
    }

    @Override
    public void deleteTeamById(int id) {
        final String DELETE_TEAM_BY_ID = "DELETE FROM team WHERE id = ?";
        jdbc.update(DELETE_TEAM_BY_ID, id);
    }
    
    public static final class TeamMapper implements RowMapper<Team> {
        @Override
        public Team mapRow(ResultSet rs, int index) throws SQLException {
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            team.setAcronym(rs.getString("acronym"));
            return team;
        }
    }
    
}
