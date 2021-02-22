/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.League;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.Pokemon;
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
public class PokemonDaoDB implements PokemonDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public PokemonDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        final String GET_ALL_POKEMON = "SELECT * FROM pokemon";
        return jdbc.query(GET_ALL_POKEMON, new PokemonMapper());
    }
    
    @Override
    public Pokemon getPokemonById(int id) {
        try {
            final String GET_POKEMON_BY_ID = "SELECT * FROM pokemon WHERE id = ?";
            return jdbc.queryForObject(GET_POKEMON_BY_ID, new PokemonMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Pokemon> getPokemonOn(int teamId, int leagueId) {
        final String GET_TEAM_FOR = "SELECT p.* FROM pokemon AS p "
                + "JOIN roster AS r ON r.pokeId = p.id "
                + "WHERE r.teamId = ? AND r.leagueId = ?";
        return jdbc.query(GET_TEAM_FOR, new PokemonMapper(), teamId, leagueId);
    }
    
    @Override
    public List<Pokemon> getPokemonFromTier(int tier, int leagueId, int limit, int offset) {
        final String GET_FROM_TIER = "SELECT pt.tier, p.* FROM pokemon AS p "
                + "JOIN pokemonTier AS pt ON p.id = pt.pokemonId "
                + "WHERE tier = ? AND leagueId = ? "
                + "LIMIT ? OFFSET ?";
        return jdbc.query(GET_FROM_TIER, new PokemonMapper(), tier, leagueId, limit, offset);
    }

    @Override
    public Pokemon getPokemonWithStatsFrom(int pokeId, Match match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pokemon getPokemonWithStatsFrom(int pokeId, League league) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pokemon addPokemon(Pokemon pokemon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePokemonById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class PokemonMapper implements RowMapper<Pokemon> {
        @Override
        public Pokemon mapRow(ResultSet rs, int index) throws SQLException {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt("id"));
            pokemon.setImgId(rs.getString("imgId"));
            pokemon.setName(rs.getString("name"));
            return pokemon;
        }
    }
    
}
