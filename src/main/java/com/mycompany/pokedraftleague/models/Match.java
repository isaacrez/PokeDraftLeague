/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import com.mycompany.pokedraftleague.models.pokemon.PokemonStats;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class Match {
    
    private int id;
    
    private List<Team> teams;
    private List<PokemonStats> pokemonParticipants;
    
    private String status;
    private Date dateSubmitted;
    private int scheduledWeek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<PokemonStats> getPokemonParticipants() {
        return pokemonParticipants;
    }

    public void setPokemonParticipants(List<PokemonStats> pokemonParticipants) {
        this.pokemonParticipants = pokemonParticipants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date completedOn) {
        this.dateSubmitted = completedOn;
    }

    public int getScheduledWeek() {
        return scheduledWeek;
    }

    public void setScheduledWeek(int scheduledWeek) {
        this.scheduledWeek = scheduledWeek;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.teams);
        hash = 13 * hash + Objects.hashCode(this.pokemonParticipants);
        hash = 13 * hash + Objects.hashCode(this.status);
        hash = 13 * hash + Objects.hashCode(this.dateSubmitted);
        hash = 13 * hash + this.scheduledWeek;
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
        final Match other = (Match) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.scheduledWeek != other.scheduledWeek) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.teams, other.teams)) {
            return false;
        }
        if (!Objects.equals(this.pokemonParticipants, other.pokemonParticipants)) {
            return false;
        }
        if (!Objects.equals(this.dateSubmitted, other.dateSubmitted)) {
            return false;
        }
        return true;
    }
    
}
