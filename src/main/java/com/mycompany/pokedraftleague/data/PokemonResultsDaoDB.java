/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.PokemonResults;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author isaacrez
 */
public class PokemonResultsDaoDB implements PokemonResultsDao {

    @Override
    public List<PokemonResults> getAllPokemonResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PokemonResults> getAllResultsForTeam(int teamId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
