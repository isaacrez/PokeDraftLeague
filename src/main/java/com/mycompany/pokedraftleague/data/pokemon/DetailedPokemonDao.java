/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.DetailedPokemon;
import com.mycompany.pokedraftleague.models.PackagedResult;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface DetailedPokemonDao {
    PackagedResult<DetailedPokemon> getAllPokemon();
    PackagedResult<DetailedPokemon> getSliceOfPokemon(int limit, int offset);
    PackagedResult<DetailedPokemon> getSliceOfPokemon(int leagueId, int limit, int offset);
    List<DetailedPokemon> getPokemonFromRoster(int teamId, int leagueId);
}
