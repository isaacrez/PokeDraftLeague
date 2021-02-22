/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.League;
import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.Pokemon;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface PokemonDao {
    List<Pokemon> getAllPokemon();
    Pokemon getPokemonById(int id);
    List<Pokemon> getPokemonOn(int teamId, int leagueId);
    Pokemon getPokemonWithStatsFrom(int pokeId, Match match);
    Pokemon getPokemonWithStatsFrom(int pokeId, League league);
    List<Pokemon> getPokemonFromTier(int tier, int leagueId, int offset, int limit);
    Pokemon addPokemon(Pokemon pokemon);
    void updatePokemon(Pokemon pokemon);
    void deletePokemonById(int id);
}
