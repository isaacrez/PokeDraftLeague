/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.DetailedPokemon;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface DetailedPokemonDao {
    List<DetailedPokemon> getAllPokemon();
    List<DetailedPokemon> getSliceOfPokemon(int limit, int offset);
    List<DetailedPokemon> getPokemonFromRoster(int teamId, int leagueId);
}
