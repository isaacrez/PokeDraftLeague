/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.AggregatePokemonStats;
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
public class AggregatePokemonStatsDaoDB implements AggregatePokemonStatsDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    PokemonDao pokemonDao;
    
    AggregatePokemonStatsDaoDB(JdbcTemplate jdbc, PokemonDao pokemonDao) {
        this.jdbc = jdbc;
        this.pokemonDao = pokemonDao;
    }

    @Override
    public AggregatePokemonStats getStatsFor(int pokeId, int leagueId) {
        Pokemon pokemon = pokemonDao.getPokemonById(pokeId);
        
        if (pokemon != null) {
            final String GET_RESULTS_FOR = "SELECT COUNT(*), SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON m.id = ma.matchId "
                    + "WHERE ma.pokeId = ? AND m.leagueId = ?";
            AggregatePokemonStats results = jdbc.queryForObject(GET_RESULTS_FOR,
                    new AggregatePokemonStatsMapper(),
                    pokeId,
                    leagueId);
            results.setPokemon(pokemonDao.getPokemonById(pokeId));
            
            return results;
        } else {
            return null;
        }
    }

    @Override
    public AggregatePokemonStats getStatsFor(int pokeId, int teamId, int leagueId) {
        Pokemon pokemon = pokemonDao.getPokemonById(pokeId);
        
        if (pokemon != null) {
            final String GET_RESULTS_FOR = "SELECT COUNT(*), SUM(directKOs), "
                    + "SUM(indirectKOs), SUM(wasKOed) "
                    + "FROM matchAttendee AS ma "
                    + "JOIN `match` AS m ON m.id = ma.matchId "
                    + "WHERE ma.pokeId = ? AND ma.teamId = ? AND m.leagueId = ?";
            AggregatePokemonStats results = jdbc.queryForObject(GET_RESULTS_FOR,
                    new AggregatePokemonStatsMapper(),
                    pokeId,
                    teamId,
                    leagueId);
            results.setPokemon(pokemon);

            return results;
        } else {
            return null;
        }
    }
          
    public static final class AggregatePokemonStatsMapper implements RowMapper<AggregatePokemonStats> {
        @Override
        public AggregatePokemonStats mapRow(ResultSet rs, int i) throws SQLException {
            AggregatePokemonStats results = new AggregatePokemonStats();
            results.setGamesPlayed(rs.getInt("COUNT(*)"));
            results.setDirectKOs(rs.getInt("SUM(directKOs)"));
            results.setIndirectKOs(rs.getInt("SUM(indirectKOs)"));
            results.setDeaths(rs.getInt("SUM(wasKOed)"));
            return results;
        }
    }
}
