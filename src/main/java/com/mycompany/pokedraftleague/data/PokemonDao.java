/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

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
    List<Pokemon> getFormsFor(String name);
    Pokemon getPokemonWithStatsFrom(int pokeId, Match match);
    Pokemon getPokemonWithStatsFrom(int pokeId, League league);
    Pokemon addPokemon(Pokemon pokemon);
    void updatePokemon(Pokemon pokemon);
    void deletePokemonById(int id);
}
