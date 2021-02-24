/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.PokemonResults;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface PokemonResultsDao {
    List<PokemonResults> getAllResultsForTeam(int teamId);
    List<PokemonResults> getAllPokemonInMatch(int matchId);
    List<PokemonResults> getPokemonInMatchFor(int matchId, int teamId);
    PokemonResults getPokemonResultsById(int id);
    PokemonResults addPokemonResults(PokemonResults pokemonResults);
    void updatePokemonResults(PokemonResults pokemonResults);
    void deletePokemonResultsById(int id);
}
