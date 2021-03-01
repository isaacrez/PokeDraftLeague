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
public class AggregatePokemonStats extends PokemonStats {
    
    int gamesPlayed;
    int gamesWon;

    public AggregatePokemonStats() {
        super();
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }
    
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.gamesPlayed;
        hash = 31 * hash + this.gamesWon;
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
        final AggregatePokemonStats other = (AggregatePokemonStats) obj;
        if (this.gamesPlayed != other.gamesPlayed) {
            return false;
        }
        if (this.gamesWon != other.gamesWon) {
            return false;
        }
        return true;
    }
}
