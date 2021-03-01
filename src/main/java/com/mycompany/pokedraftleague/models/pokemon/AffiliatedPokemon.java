/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models.pokemon;

import com.mycompany.pokedraftleague.models.MinimumTeam;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class AffiliatedPokemon extends Pokemon {
    
    private MinimumTeam team;
    
    public MinimumTeam getTeam() {
        return team;
    }

    public void setTeam(MinimumTeam team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.team);
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
        final AffiliatedPokemon other = (AffiliatedPokemon) obj;
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }
}
