/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import com.mycompany.pokedraftleague.models.pokemon.Pokemon;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class Roster {
    
    private Team team;
    private List<Pokemon> roster;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Pokemon> getRoster() {
        return roster;
    }

    public void setRoster(List<Pokemon> roster) {
        this.roster = roster;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.team);
        hash = 59 * hash + Objects.hashCode(this.roster);
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
        final Roster other = (Roster) obj;
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        if (!Objects.equals(this.roster, other.roster)) {
            return false;
        }
        return true;
    }
    
}
