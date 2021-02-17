/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.AggregatePokemonResults;

/**
 *
 * @author isaacrez
 */
public interface AggregatePokemonResultsDao {
    AggregatePokemonResults getResultsFor(int pokeId, int leagueId);
    AggregatePokemonResults getResultsFor(int pokeId, int teamId, int leagueId);
}
