/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.data.PokemonDaoDB.PokemonMapper;
import com.mycompany.pokedraftleague.models.Pokemon;
import com.mycompany.pokedraftleague.models.PokemonResults;
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
public class PokemonResultsDaoDB implements PokemonResultsDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public PokemonResultsDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<PokemonResults> getAllPokemonResults() {
        final String GET_ALL_POKE_RESULTS = "SELECT * FROM matchAttendee";
        List<PokemonResults> results = jdbc.query(GET_ALL_POKE_RESULTS, new PokemonResultsMapper());
        addPokemonToResults(results);
        return results;
    }

    @Override
    public List<PokemonResults> getAllResultsForTeam(int teamId) {
        final String GET_RESULTS_FOR_TEAM = "SELECT * FROM matchAttendee WHERE teamId = ?";
        List<PokemonResults> results = jdbc.query(GET_RESULTS_FOR_TEAM, new PokemonResultsMapper());
        addPokemonToResults(results);
        return results;
    }
    
    @Override
    public List<PokemonResults> getAllPokemonInMatch(int matchId) {
        final String GET_ALL_POKEMON_IN_MATCH = "SELECT * FROM matchAttendee "
                + "WHERE matchId = ?";
        List<PokemonResults> results = jdbc.query(GET_ALL_POKEMON_IN_MATCH,
                new PokemonResultsMapper(),
                matchId);
        addPokemonToResults(results);
        return results;
    }

    @Override
    public List<PokemonResults> getPokemonInMatchFor(int matchId, int teamId) {
        final String GET_POKEMON_IN_MATCH = "SELECT * FROM matchAttendee "
                + "WHERE matchId = ? AND teamId = ?";
        List<PokemonResults> results = jdbc.query(GET_POKEMON_IN_MATCH,
                new PokemonResultsMapper(),
                matchId,
                teamId);
        addPokemonToResults(results);
        return results;
    }
    
    @Override
    public PokemonResults getPokemonResultsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public PokemonResults getResultsFor(int pokeId, int teamId, int leagueId) {
        try {            
            final String GET_RESULTS_FOR = "SELECT ma.teamId, SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON ma.matchId = m.id "
                    + "WHERE ma.pokeId = ? AND ma.teamId = ? AND m.leagueId = ?";
            
            PokemonResults result = jdbc.queryForObject(GET_RESULTS_FOR,
                    new AggregatePokemonResultsMapper(),
                    pokeId,
                    teamId,
                    leagueId);
            result.setPokemon(getPokemonForResults(pokeId));
            return result;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public PokemonResults addPokemonResults(PokemonResults pokemonResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePokemonResults(PokemonResults pokemonResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePokemonResultsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addPokemonToResults(List<PokemonResults> results) {
        for (PokemonResults result : results) {
            result.setPokemon(getPokemonForResults(result));
        }
    }
    
    private Pokemon getPokemonForResults(PokemonResults pokemonResults) {
        final String GET_POKE_ID = "SELECT pokeId FROM matchAttendee WHERE id = ?";
        int pokeId = jdbc.queryForObject(GET_POKE_ID, Integer.class, pokemonResults.getId());
        return getPokemonForResults(pokeId);
    }
    
    private Pokemon getPokemonForResults(int pokeId) {
        final String GET_POKEMON = "SELECT p.* FROM pokemon AS p "
                + "JOIN matchAttendee AS ma ON ma.pokeId = p.id "
                + "WHERE ma.pokeId = ?";
        return jdbc.queryForObject(GET_POKEMON, new PokemonMapper(), pokeId);
    }
    
    public static final class AggregatePokemonResultsMapper implements RowMapper<PokemonResults> {
        @Override
        public PokemonResults mapRow(ResultSet rs, int i) throws SQLException {
            PokemonResults results = new PokemonResults();
            results.setTeamId(rs.getInt("teamId"));
            results.setDirectKOs(rs.getInt("SUM(directKOs)"));
            results.setIndirectKOs(rs.getInt("SUM(indirectKOs)"));
            results.setDeaths(rs.getInt("SUM(wasKOed)"));
            return results;
        }
    }
    
    public static final class PokemonResultsMapper implements RowMapper<PokemonResults> {
        @Override
        public PokemonResults mapRow(ResultSet rs, int index) throws SQLException {
            PokemonResults pokemonResults = new PokemonResults();
            pokemonResults.setId(rs.getInt("id"));
            pokemonResults.setTeamId(rs.getInt("teamId"));
            pokemonResults.setDirectKOs(rs.getInt("directKOs"));
            pokemonResults.setIndirectKOs(rs.getInt("indirectKOs"));
            pokemonResults.setDeaths(rs.getInt("wasKOed"));
            return pokemonResults;
        }
    }
}
