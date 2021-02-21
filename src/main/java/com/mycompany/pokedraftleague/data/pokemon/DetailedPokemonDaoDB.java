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
        List<DetailedPokemon> pokemon =  jdbc.query(GET_ALL,
                new DetailedPokemonMapper());
        addTypingAndAbilities(pokemon);
        return pokemon;
    }

    @Override
    public List<DetailedPokemon> getSliceOfPokemon(int limit, int offset) {
        final String GET_SLICE = "SELECT * FROM pokemon LIMIT ? OFFSET ?";
        List<DetailedPokemon> pokemon = jdbc.query(GET_SLICE,
                new DetailedPokemonMapper(),
                limit,
                offset);
        addTypingAndAbilities(pokemon);
        return pokemon;
    }
    
    @Override
    public List<DetailedPokemon> getPokemonFromRoster(int teamId, int leagueId) {
        final String GET_FOR_TEAM = "SELECT p.* FROM pokemon AS p "
                + "JOIN roster AS r ON p.id = r.pokeId "
                + "WHERE teamId = ? AND leagueId = ?";
        List<DetailedPokemon> pokemon = jdbc.query(GET_FOR_TEAM, 
                new DetailedPokemonMapper(),
                teamId,
                leagueId);
        addTypingAndAbilities(pokemon);
        return pokemon;
    }
    
    private void addTypingAndAbilities(List<DetailedPokemon> pokemon) {
        for (DetailedPokemon poke : pokemon) {
            addTypingAndAbilities(poke);
        }
    }
    
    private void addTypingAndAbilities(DetailedPokemon pokemon) {
        addTyping(pokemon);
        addAbilities(pokemon);
    }
    
    private void addTyping(DetailedPokemon pokemon) {
        final String GET_TYPE = "SELECT t.name FROM `type` AS t "
                + "JOIN pokemonType AS pt ON t.id = pt.typeId "
                + "JOIN pokemon AS p ON pt.pokemonId = p.id "
                + "WHERE p.id = ?";
        
        int id = pokemon.getPokemon().getId();
        List<String> type = jdbc.query(GET_TYPE, new NameMapper(), id);
        pokemon.setType(type);
    }
    
    private void addAbilities(DetailedPokemon pokemon) {
        final String GET_ABILITIES = "SELECT a.name FROM ability AS a "
                + "JOIN pokemonAbility AS pa ON a.id = pa.abilityId "
                + "JOIN pokemon AS p ON pa.pokemonId = p.id "
                + "WHERE p.id = ?";
        
        int id = pokemon.getPokemon().getId();
        List<String> abilities = jdbc.query(GET_ABILITIES, new NameMapper(), id);
        pokemon.setAbilities(abilities);
        
    }
    
    public static final class NameMapper implements RowMapper<String> {
        
        @Override
        public String mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getString("name");
        }
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
