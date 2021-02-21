/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.DetailedPokemon;
import com.mycompany.pokedraftleague.models.Pokemon;
import com.mycompany.pokedraftleague.models.BaseStats;
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
public class DetailedPokemonDaoDB implements DetailedPokemonDao {
    
    JdbcTemplate jdbc;

    @Autowired
    public DetailedPokemonDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public List<DetailedPokemon> getAllPokemon() {
        final String GET_ALL = "SELECT * FROM pokemon";
        return jdbc.query(GET_ALL, new DetailedPokemonMapper());
    }

    @Override
    public List<DetailedPokemon> getSliceOfPokemon(int limit, int offset) {
        final String GET_SLICE = "SELECT * FROM pokemon LIMIT ? OFFSET ?";
        return jdbc.query(GET_SLICE,
                new DetailedPokemonMapper(),
                limit,
                offset);
    }
    
    public static final class DetailedPokemonMapper implements RowMapper<DetailedPokemon> {

        @Override
        public DetailedPokemon mapRow(ResultSet rs, int i) throws SQLException {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt("id"));
            pokemon.setImgId(rs.getString("imgId"));
            pokemon.setName(rs.getString("name"));
            
            BaseStats stats = new BaseStats();
            stats.setHP(rs.getInt("HP"));
            stats.setAtk(rs.getInt("Atk"));
            stats.setDef(rs.getInt("Def"));
            stats.setSpAtk(rs.getInt("SpA"));
            stats.setSpDef(rs.getInt("SpD"));
            stats.setSpe(rs.getInt("Spe"));
            
            DetailedPokemon fullPokemon = new DetailedPokemon();
            fullPokemon.setPokemon(pokemon);
            fullPokemon.setStats(stats);
            return fullPokemon;
        }
    }
}
