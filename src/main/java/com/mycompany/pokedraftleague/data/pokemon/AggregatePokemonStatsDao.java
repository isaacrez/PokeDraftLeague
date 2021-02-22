/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.pokemon;

import com.mycompany.pokedraftleague.models.AggregatePokemonStats;

/**
 *
 * @author isaacrez
 */
public interface AggregatePokemonStatsDao {
    AggregatePokemonStats getStatsFor(int pokeId, int leagueId);
    AggregatePokemonStats getStatsFor(int pokeId, int teamId, int leagueId);
}
