/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.data.league.TeamDao;
import com.mycompany.pokedraftleague.models.League;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.PackagedResult;
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
    private final TeamDao teamDao;
    
    @Autowired
    public PokemonDaoDB(JdbcTemplate jdbcTemplate, TeamDao teamDao) {
        this.jdbc = jdbcTemplate;
        this.teamDao = teamDao;
    }

    @Override
    public PackagedResult<Pokemon> getAllPokemon() {
        final String GET_ALL_POKEMON = "SELECT * FROM pokemon";
        List<Pokemon> pokemon = jdbc.query(GET_ALL_POKEMON, new PokemonMapper());
        
        final String GET_COUNT = "SELECT COUNT(*) FROM pokemon";
        int count = jdbc.queryForObject(GET_COUNT, Integer.class);
        
        PackagedResult<Pokemon> output = new PackagedResult(count, pokemon);
        return output;
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
    public List<Pokemon> getPokemonFromTier(String tier, int leagueId) {
        final String GET_FROM_TIER = "SELECT t.label AS tier, p.* FROM pokemon AS p "
                + "JOIN pokemonTier AS pt ON p.id = pt.pokemonId "
                + "JOIN tier AS t ON pt.tierId = t.id "
                + "WHERE t.label = ? AND leagueId = ?";
        List<Pokemon> pokemon = jdbc.query(GET_FROM_TIER,
                new PokemonMapper(),
                tier,
                leagueId);
        addTeamToPokemon(pokemon, leagueId);
        return pokemon;
    }

    private void addTeamToPokemon(List<Pokemon> pokemon, int leagueId) {
        for (Pokemon poke : pokemon) {
            addTeamToPokemon(poke, leagueId);
        }
    }
    
    private void addTeamToPokemon(Pokemon pokemon, int leagueId) {
        pokemon.setTeam(teamDao.getTeamOfPokemonInLeague(pokemon.getId(), leagueId));
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
