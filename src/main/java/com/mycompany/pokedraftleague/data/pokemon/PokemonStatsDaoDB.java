/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.data.league.TeamDao;
import com.mycompany.pokedraftleague.models.Pokemon;
import com.mycompany.pokedraftleague.models.PokemonStats;
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
public class PokemonStatsDaoDB implements PokemonStatsDao {
    
    private final JdbcTemplate jdbc;
    private final PokemonDao pokemonDao;
    private final TeamDao teamDao;
    
    @Autowired
    public PokemonStatsDaoDB(JdbcTemplate jdbc, PokemonDao pokemonDao, TeamDao teamDao) {
        this.jdbc = jdbc;
        this.pokemonDao = pokemonDao;
        this.teamDao = teamDao;
    }

    @Override
    public List<PokemonStats> getAllResultsForTeam(int teamId) {
        final String GET_RESULTS_FOR_TEAM = "SELECT * FROM matchAttendee WHERE teamId = ?";
        List<PokemonStats> results = jdbc.query(GET_RESULTS_FOR_TEAM, new PokemonStatsMapper());
        addPropertiesToResults(results);
        return results;
    }
    
    @Override
    public List<PokemonStats> getAllPokemonInMatch(int teamId1, int teamId2, int leagueId) {
        try {
            final String GET_MATCH_ID = "SELECT m.id FROM matchTeam AS mt1 "
                    + "JOIN matchTeam AS mt2 ON mt1.matchId = mt2.matchId "
                    + "JOIN `match` AS m ON mt1.matchId = m.id "
                    + "WHERE mt1.teamId = ? AND mt2.teamId = ? AND m.leagueId = ?";
            int matchId = jdbc.queryForObject(GET_MATCH_ID,
                    Integer.class,
                    teamId1,
                    teamId2,
                    leagueId);
            return getAllPokemonInMatch(matchId);
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<PokemonStats> getAllPokemonInMatch(int matchId) {
        List<Team> teams = teamDao.getTeamsByMatchId(matchId);
        List<PokemonStats> results = getPokemonInMatchFor(matchId, teams.get(0).getId());
        results.addAll(getPokemonInMatchFor(matchId, teams.get(1).getId()));
        return results;
    }

    @Override
    public List<PokemonStats> getPokemonInMatchFor(int matchId, int teamId) {
        final String GET_POKEMON_IN_MATCH = "SELECT * FROM matchAttendee "
                + "WHERE matchId = ? AND teamId = ?";
        List<PokemonStats> results = jdbc.query(GET_POKEMON_IN_MATCH,
                new PokemonStatsMapper(),
                matchId,
                teamId);
        addPropertiesToResults(results);
        return results;
    }
    
    @Override
    public PokemonStats getPokemonResultsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PokemonStats addPokemonResults(PokemonStats pokemonResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePokemonResults(PokemonStats pokemonResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePokemonResultsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addPropertiesToResults(List<PokemonStats> results) {
        for (PokemonStats result : results) {
            result.setPokemon(getPokemonForResults(result));
        }
    }
    
    private Pokemon getPokemonForResults(PokemonStats pokemonResults) {
        final String GET_POKE_ID = "SELECT pokeId FROM matchAttendee WHERE id = ?";
        int pokeId = jdbc.queryForObject(GET_POKE_ID, Integer.class, pokemonResults.getId());
        return getPokemonForResults(pokeId);
    }
    
    private Pokemon getPokemonForResults(int pokeId) {
        return pokemonDao.getPokemonById(pokeId);
    }
    
    public static final class PokemonStatsMapper implements RowMapper<PokemonStats> {
        @Override
        public PokemonStats mapRow(ResultSet rs, int index) throws SQLException {
            PokemonStats pokemonStats = new PokemonStats();
            pokemonStats.setId(rs.getInt("id"));
            pokemonStats.setDirectKOs(rs.getInt("directKOs"));
            pokemonStats.setIndirectKOs(rs.getInt("indirectKOs"));
            pokemonStats.setDeaths(rs.getInt("wasKOed"));
            return pokemonStats;
        }
    }
}
