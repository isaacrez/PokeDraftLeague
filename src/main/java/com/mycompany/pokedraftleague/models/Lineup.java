/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import com.mycompany.pokedraftleague.models.pokemon.PokemonStats;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class Lineup {
    
    private Team team;
    private List<PokemonStats> pokemon;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PokemonStats> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<PokemonStats> pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.team);
        hash = 83 * hash + Objects.hashCode(this.pokemon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lineup other = (Lineup) obj;
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        if (!Objects.equals(this.pokemon, other.pokemon)) {
            return false;
        }
        return true;
    }
}
