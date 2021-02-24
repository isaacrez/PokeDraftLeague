/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.data.league.TeamDao;
import com.mycompany.pokedraftleague.models.Pokemon;
import com.mycompany.pokedraftleague.models.PokemonResults;
import com.mycompany.pokedraftleague.models.Team;
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
    private final PokemonDao pokemonDao;
    private final TeamDao teamDao;
    
    @Autowired
    public PokemonResultsDaoDB(JdbcTemplate jdbc, PokemonDao pokemonDao, TeamDao teamDao) {
        this.jdbc = jdbc;
        this.pokemonDao = pokemonDao;
        this.teamDao = teamDao;
    }

    @Override
    public List<PokemonResults> getAllResultsForTeam(int teamId) {
        final String GET_RESULTS_FOR_TEAM = "SELECT * FROM matchAttendee WHERE teamId = ?";
        List<PokemonResults> results = jdbc.query(GET_RESULTS_FOR_TEAM, new PokemonResultsMapper());
        addPropertiesToResults(results, teamId);
        return results;
    }
    
    @Override
    public List<PokemonResults> getAllPokemonInMatch(int teamId1, int teamId2) {
        final String GET_MATCH_ID = "SELECT mt1.matchId FROM matchTeam AS mt1 "
                + "JOIN matchTeam AS mt2 ON mt1.matchId = mt2.matchId "
                + "WHERE mt1.teamId = ? AND mt2.teamId = ?";
        int matchId = jdbc.queryForObject(GET_MATCH_ID, Integer.class, teamId1, teamId2);
        return getAllPokemonInMatch(matchId);
    }
    
    @Override
    public List<PokemonResults> getAllPokemonInMatch(int matchId) {
        List<Team> teams = teamDao.getTeamsByMatchId(matchId);
        List<PokemonResults> results = getPokemonInMatchFor(matchId, teams.get(0).getId());
        results.addAll(getPokemonInMatchFor(matchId, teams.get(1).getId()));
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
        addPropertiesToResults(results, teamId);
        return results;
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
    
    private void addPropertiesToResults(List<PokemonResults> results, int teamId) {
        Team team = teamDao.getTeamById(teamId);
        for (PokemonResults result : results) {
            result.setPokemon(getPokemonForResults(result));
            result.getPokemon().setTeam(team);
        }
    }
    
    private Pokemon getPokemonForResults(PokemonResults pokemonResults) {
        final String GET_POKE_ID = "SELECT pokeId FROM matchAttendee WHERE id = ?";
        int pokeId = jdbc.queryForObject(GET_POKE_ID, Integer.class, pokemonResults.getId());
        return getPokemonForResults(pokeId);
    }
    
    private Pokemon getPokemonForResults(int pokeId) {
        return pokemonDao.getPokemonById(pokeId);
    }
    
    public static final class PokemonResultsMapper implements RowMapper<PokemonResults> {
        @Override
        public PokemonResults mapRow(ResultSet rs, int index) throws SQLException {
            PokemonResults pokemonResults = new PokemonResults();
            pokemonResults.setId(rs.getInt("id"));
            pokemonResults.setDirectKOs(rs.getInt("directKOs"));
            pokemonResults.setIndirectKOs(rs.getInt("indirectKOs"));
            pokemonResults.setDeaths(rs.getInt("wasKOed"));
            return pokemonResults;
        }
    }
}
