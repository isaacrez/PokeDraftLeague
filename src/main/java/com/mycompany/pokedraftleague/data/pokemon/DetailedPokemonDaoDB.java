/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.pokemon.DetailedPokemon;
import com.mycompany.pokedraftleague.models.pokemon.Pokemon;
import com.mycompany.pokedraftleague.models.pokemon.BaseStats;
import com.mycompany.pokedraftleague.models.PackagedResult;
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
    public PackagedResult<DetailedPokemon> getAllPokemon() {
        final String GET_ALL = "SELECT NULL AS tier, p.* FROM pokemon AS p";
        List<DetailedPokemon> pokemon =  jdbc.query(GET_ALL,
                new DetailedPokemonMapper());
        addTypingAndAbilities(pokemon);
        
        return new PackagedResult(getCount(), pokemon);
    }
    
    @Override
    public PackagedResult<DetailedPokemon> getSliceOfPokemon(int limit, int offset) {
        final String GET_SLICE = "SELECT NULL AS tier, p.* FROM pokemon AS p "
                + "LIMIT ? OFFSET ?";
        List<DetailedPokemon> pokemon = jdbc.query(GET_SLICE,
                new DetailedPokemonMapper(),
                limit,
                offset);
        addTypingAndAbilities(pokemon);
        
        return new PackagedResult(getCount(), pokemon);
    }

    @Override
    public PackagedResult<DetailedPokemon> getSliceOfPokemon(int leagueId, int limit, int offset) {
        final String GET_SLICE = "SELECT t.value AS tier, p.* FROM pokemon AS p "
                + "JOIN pokemonTier AS pt ON p.id = pt.pokemonId "
                + "JOIN tier AS t ON pt.tierId = t.id "
                + "WHERE pt.leagueId = ? "
                + "ORDER BY id "
                + "LIMIT ? OFFSET ?";
        List<DetailedPokemon> pokemon = jdbc.query(GET_SLICE,
                new DetailedPokemonMapper(),
                leagueId,
                limit,
                offset);
        addTypingAndAbilities(pokemon);
        
        return new PackagedResult(getCount(leagueId), pokemon);
    }
    
    @Override
    public List<DetailedPokemon> getPokemonFromRoster(int teamId, int leagueId) {
        final String GET_FOR_TEAM = "SELECT t.value AS tier, p.* FROM pokemon AS p "
                + "JOIN roster AS r ON p.id = r.pokeId "
                + "JOIN pokemonTier AS pt ON p.id = pt.pokemonId "
                + "JOIN tier AS t ON pt.tierId = t.id "
                + "WHERE r.teamId = ? AND r.leagueId = pt.leagueId "
                + "AND pt.leagueId = ?";
        List<DetailedPokemon> pokemon = jdbc.query(GET_FOR_TEAM, 
                new DetailedPokemonMapper(),
                teamId,
                leagueId);
        addTypingAndAbilities(pokemon);
        return pokemon;
    }
    
    private int getCount() {
        final String GET_COUNT = "SELECT COUNT(*) FROM pokemon";
        return jdbc.queryForObject(GET_COUNT, Integer.class);
    }
    
    private int getCount(int leagueId) {
        final String GET_COUNT = "SELECT COUNT(*) FROM pokemonTier "
                + "WHERE leagueId = ?";
        return jdbc.queryForObject(GET_COUNT, Integer.class, leagueId);
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
        pokemon.setType(jdbc.queryForList(GET_TYPE, String.class, id));
    }
    
    private void addAbilities(DetailedPokemon pokemon) {
        final String GET_ABILITIES = "SELECT a.name FROM ability AS a "
                + "JOIN pokemonAbility AS pa ON a.id = pa.abilityId "
                + "JOIN pokemon AS p ON pa.pokemonId = p.id "
                + "WHERE p.id = ?";
        
        int id = pokemon.getPokemon().getId();
        pokemon.setAbilities(jdbc.queryForList(GET_ABILITIES, String.class, id));
        
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
            fullPokemon.setTier(rs.getString("tier"));
            return fullPokemon;
        }
    }
}
