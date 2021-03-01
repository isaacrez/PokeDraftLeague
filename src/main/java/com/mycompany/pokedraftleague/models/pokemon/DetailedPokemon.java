/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models.pokemon;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class DetailedPokemon extends AffiliatedPokemon {
    
    private String tier;
    private BaseStats stats;
    private List<String> type;
    private List<String> abilities;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
    
    public BaseStats getStats() {
        return stats;
    }

    public void setStats(BaseStats stats) {
        this.stats = stats;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.tier);
        hash = 37 * hash + Objects.hashCode(this.stats);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.abilities);
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
        final DetailedPokemon other = (DetailedPokemon) obj;
        if (!Objects.equals(this.tier, other.tier)) {
            return false;
        }
        if (!Objects.equals(this.stats, other.stats)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.abilities, other.abilities)) {
            return false;
        }
        return true;
    }
}
