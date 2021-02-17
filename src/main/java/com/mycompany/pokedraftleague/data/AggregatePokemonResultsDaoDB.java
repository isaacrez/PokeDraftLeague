/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.AggregatePokemonResults;
import com.mycompany.pokedraftleague.models.Pokemon;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author isaacrez
 */
@Repository
public class AggregatePokemonResultsDaoDB implements AggregatePokemonResultsDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    PokemonDao pokemonDao;
    
    AggregatePokemonResultsDaoDB(JdbcTemplate jdbc, PokemonDao pokemonDao) {
        this.jdbc = jdbc;
        this.pokemonDao = pokemonDao;
    }

    @Override
    public AggregatePokemonResults getResultsFor(int pokeId, int leagueId) {
        Pokemon pokemon = pokemonDao.getPokemonById(pokeId);
        
        if (pokemon != null) {
            final String GET_RESULTS_FOR = "SELECT COUNT(*), SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON m.id = ma.matchId "
                    + "WHERE ma.pokeId = ? AND m.leagueId = ?";
            AggregatePokemonResults results = jdbc.queryForObject(GET_RESULTS_FOR,
                    new AggregatePokemonResultsMapper(),
                    pokeId,
                    leagueId);
            results.setPokemon(pokemonDao.getPokemonById(pokeId));
            
            return results;
        } else {
            return null;
        }
    }

    @Override
    public AggregatePokemonResults getResultsFor(int pokeId, int teamId, int leagueId) {
        Pokemon pokemon = pokemonDao.getPokemonById(pokeId);
        
        if (pokemon != null) {
            final String GET_RESULTS_FOR = "SELECT COUNT(*), SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON m.id = ma.matchId "
                    + "WHERE ma.pokeId = ? AND ma.teamId = ? AND m.leagueId = ?";
            AggregatePokemonResults results = jdbc.queryForObject(GET_RESULTS_FOR,
                    new AggregatePokemonResultsMapper(),
                    pokeId,
                    teamId,
                    leagueId);
            results.setPokemon(pokemon);

            return results;
        } else {
            return null;
        }
    }
          
    public static final class AggregatePokemonResultsMapper implements RowMapper<AggregatePokemonResults> {
        @Override
        public AggregatePokemonResults mapRow(ResultSet rs, int i) throws SQLException {
            AggregatePokemonResults results = new AggregatePokemonResults();
            results.setGamesPlayed(rs.getInt("COUNT(*)"));
            results.setDirectKOs(rs.getInt("SUM(directKOs)"));
            results.setIndirectKOs(rs.getInt("SUM(indirectKOs)"));
            results.setDeaths(rs.getInt("SUM(wasKOed)"));
            return results;
        }
    }
}
