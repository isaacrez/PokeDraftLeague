/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class Lineup {
    
    private List<Pokemon> lineup;
    
    public Lineup() {
        lineup = new ArrayList<>();
    }
    
    public Lineup(List<Pokemon> pokemon) {
        lineup = pokemon;
    }
    
    public void addPokemon(Pokemon pokemon) {
        lineup.add(pokemon);
    }
    
    public void addPokemon(List<Pokemon> pokemon) {
        for (Pokemon poke : pokemon) {
            addPokemon(poke);
        }
    }
    
    public void clearLineup() {
        lineup.clear();
    }
    
    public List<Pokemon> getLineup() {
        return lineup;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.lineup);
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
        if (!Objects.equals(this.lineup, other.lineup)) {
            return false;
        }
        return true;
    }
    
}
