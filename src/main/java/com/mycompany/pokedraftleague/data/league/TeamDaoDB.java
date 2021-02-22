/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.league;

import com.mycompany.pokedraftleague.data.CoachDaoDB.CoachMapper;
import com.mycompany.pokedraftleague.data.pokemon.PokemonDaoDB.PokemonMapper;
import com.mycompany.pokedraftleague.models.Coach;
import com.mycompany.pokedraftleague.models.Roster;
import com.mycompany.pokedraftleague.models.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Team> teams = jdbc.query(GET_ALL_TEAMS, new TeamMapper());
        addCoachToTeams(teams);
        return teams;
    }

    @Override
    public List<Team> getAllTeamsForLeague(int leagueId) {
        final String GET_TEAM_BY_LEAGUE_ID = "SELECT t.* FROM team AS t "
                + "JOIN leagueteam AS lt ON t.id = lt.teamId "
                + "WHERE lt.leagueId = ?";
        List<Team> teams = jdbc.query(GET_TEAM_BY_LEAGUE_ID, new TeamMapper(), leagueId);
        addCoachToTeams(teams);
        return teams;
    }

    @Override
    public Roster getRosterById(int teamId, int leagueId) {
        try {
            Roster roster = new Roster();

            final String GET_POKEMON_BY_TEAM_ID = "SELECT p.* FROM team AS t "
                    + "JOIN roster AS r ON t.id = r.teamId "
                    + "JOIN pokemon AS p ON r.pokeId = p.id "
                    + "WHERE r.teamId = ? AND r.leagueId = ?";
            roster.setRoster(jdbc.query(GET_POKEMON_BY_TEAM_ID, new PokemonMapper(), teamId, leagueId));

            final String GET_TEAM_BY_ID = "SELECT * FROM team WHERE id = ?";
            roster.setTeam(jdbc.queryForObject(GET_TEAM_BY_ID, new TeamMapper(), teamId));

            return roster;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Roster> getAllTeamRostersForLeague(int leagueId) {
        List<Roster> roster = new ArrayList<>();
        List<Team> teams = getAllTeamsForLeague(leagueId);
        
        for (Team team : teams) {
            roster.add(getRosterById(team.getId(), leagueId));
        }
        
        return roster;
    }

    @Override
    public Team getTeamById(int id) {
        try {
            final String GET_TEAM_BY_ID = "SELECT * FROM team WHERE id = ?";
            Team team = jdbc.queryForObject(GET_TEAM_BY_ID, new TeamMapper(), id);
            addCoachToTeam(team);
            return team;
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Team> getTeamsByMatchId(int id) {
        final String GET_TEAMS_FOR_MATCH = "SELECT t.* FROM team AS t "
            + "JOIN matchteam AS mt ON t.id = mt.teamId "
            + "WHERE mt.matchId = ?";
        List<Team> teams = jdbc.query(GET_TEAMS_FOR_MATCH, new TeamMapper(), id);
        addCoachToTeams(teams);
        return teams;
    }
    
    @Override
    public Team getTeamOfPokemonInLeague(int pokeId, int leagueId) {
        try {
            final String GET_TEAM_FOR_POKE = "SELECT t.* FROM team AS t "
                    + "JOIN roster AS r ON t.id = r.teamId "
                    + "WHERE r.pokeId = ? AND r.leagueId = ?";
            Team team = jdbc.queryForObject(GET_TEAM_FOR_POKE, new TeamMapper(), pokeId, leagueId);
            addCoachToTeam(team);
            return team;
        } catch (DataAccessException e) {
            return new Team();
        }
    }

    @Override
    public Team addTeam(Team team) {
        final String ADD_TEAM = "INSERT INTO team (name, acronym, coachId) "
                + "VALUES (?, ?, ?)";
        jdbc.update(ADD_TEAM,
                team.getName(),
                team.getAcronym(),
                team.getCoach().getId());
        
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
    
    private void addCoachToTeam(Team team) {
        team.setCoach(getCoachForTeam(team));
    }
    
    private void addCoachToTeams(List<Team> teams) {
        for (Team team : teams) {
            addCoachToTeam(team);
        }
    }
    
    private Coach getCoachForTeam(Team team) {
        final String GET_COACH_FOR_TEAM = "SELECT c.* FROM coach AS c "
                + "JOIN team AS t ON t.coachId = c.id "
                + "WHERE t.id = ?";
        return jdbc.queryForObject(GET_COACH_FOR_TEAM, new CoachMapper(), team.getId());
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
