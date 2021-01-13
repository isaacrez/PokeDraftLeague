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
        final String GET_ALL_POKE_RESULTS = "SELECT * FROM matchAttendees";
        List<PokemonResults> results = jdbc.query(GET_ALL_POKE_RESULTS, new PokemonResultsMapper());
        addPokemonToResults(results);
        return results;
    }

    @Override
    public List<PokemonResults> getAllResultsForTeam(int teamId) {
        final String GET_RESULTS_FOR_TEAM = "SELECT * FROM matchAttendees WHERE teamId = ?";
        List<PokemonResults> results = jdbc.query(GET_RESULTS_FOR_TEAM, new PokemonResultsMapper());
        addPokemonToResults(results);
        return results;
    }

    @Override
    public List<PokemonResults> getPokemonResultsForTeamAndMatch(int teamId, int matchId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PokemonResults getPokemonResultsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        final String GET_POKEMON = "SELECT p.* FROM pokemon AS p "
                + "JOIN matchAttendee AS ma ON ma.pokeId = p.id "
                + "WHERE ma.id = ?";
        return jdbc.queryForObject(GET_POKEMON, new PokemonMapper(), pokemonResults.getId());
    }
    
    public static final class PokemonResultsMapper implements RowMapper<PokemonResults> {
        @Override
        public PokemonResults mapRow(ResultSet rs, int index) throws SQLException {
            PokemonResults pokemonResults = new PokemonResults();
            pokemonResults.setId(rs.getInt("id"));
            pokemonResults.setDirectKOs(rs.getInt("directKOs"));
            pokemonResults.setIndirectKOs(rs.getInt("indirectKOs"));
            pokemonResults.setDeaths(rs.getInt("deaths"));
            return pokemonResults;
        }
    }
}
