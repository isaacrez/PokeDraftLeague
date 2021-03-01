/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.PokemonStats;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface PokemonStatsDao {
    List<PokemonStats> getAllResultsForTeam(int teamId);
    List<PokemonStats> getAllPokemonInMatch(int teamId1, int teamId2, int leagueId);
    List<PokemonStats> getAllPokemonInMatch(int matchId);
    List<PokemonStats> getPokemonInMatchFor(int matchId, int teamId);
    PokemonStats getPokemonResultsById(int id);
    PokemonStats addPokemonResults(PokemonStats pokemonStats);
    void updatePokemonResults(PokemonStats pokemonStats);
    void deletePokemonResultsById(int id);
}
