/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models.pokemon;

/**
 *
 * @author isaacrez
 */
public class PokemonStats extends Pokemon {
    
    private int directKOs;
    private int indirectKOs;
    private int deaths;
    
    public PokemonStats() {
        this.directKOs = 0;
        this.indirectKOs = 0;
        this.deaths = 0;
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
        int hash = 5;
        hash = 97 * hash + this.directKOs;
        hash = 97 * hash + this.indirectKOs;
        hash = 97 * hash + this.deaths;
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
        final PokemonStats other = (PokemonStats) obj;
        if (this.directKOs != other.directKOs) {
            return false;
        }
        if (this.indirectKOs != other.indirectKOs) {
            return false;
        }
        if (this.deaths != other.deaths) {
            return false;
        }
        return true;
    }
}
