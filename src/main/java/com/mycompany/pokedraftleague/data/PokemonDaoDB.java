/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

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
    public List<Pokemon> getFormsFor(String name) {
        final String GET_FORMS_FOR = "SELECT * FROM pokemon WHERE name = ?";
        return jdbc.query(GET_FORMS_FOR, new PokemonMapper(), name);
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
            pokemon.setName(rs.getString("name"));
            pokemon.setForm(rs.getString("form"));
            return pokemon;
        }
    }
    
}
