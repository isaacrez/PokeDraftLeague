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
public class TeamResults {
    
    private Team team;
    private int teamId;
    private int gamesPlayed;
    private int gamesWon;
    private int differential;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    public int getTeamId() {
        return teamId;
    }
    
    public void setTeamId(int teamId) {
        this.teamId = teamId;
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

    public int getDifferential() {
        return differential;
    }

    public void setDifferential(int differential) {
        this.differential = differential;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.team);
        hash = 37 * hash + this.gamesPlayed;
        hash = 37 * hash + this.gamesWon;
        hash = 37 * hash + this.differential;
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
        final TeamResults other = (TeamResults) obj;
        if (this.gamesPlayed != other.gamesPlayed) {
            return false;
        }
        if (this.gamesWon != other.gamesWon) {
            return false;
        }
        if (this.differential != other.differential) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }
    
}
