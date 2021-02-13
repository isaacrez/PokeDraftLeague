/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class PokemonResults {
    
    private int id;
    private Team team;
    private Pokemon pokemon;
    private int directKOs;
    private int indirectKOs;
    private int deaths;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
    
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    
    public int getDirectKOs() {
        return directKOs;
    }

    public void setDirectKOs(int directKOs) {
        this.directKOs = directKOs;
    }

    public int getIndirectKOs() {
        return indirectKOs;
    }

    public void setIndirectKOs(int indirectKOs) {
        this.indirectKOs = indirectKOs;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.team);
        hash = 73 * hash + Objects.hashCode(this.pokemon);
        hash = 73 * hash + this.directKOs;
        hash = 73 * hash + this.indirectKOs;
        hash = 73 * hash + this.deaths;
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
        final PokemonResults other = (PokemonResults) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.directKOs != other.directKOs) {
            return false;
        }
        if (this.indirectKOs != other.indirectKOs) {
            return false;
        }
        if (this.deaths != other.deaths) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        if (!Objects.equals(this.pokemon, other.pokemon)) {
            return false;
        }
        return true;
    }

}
