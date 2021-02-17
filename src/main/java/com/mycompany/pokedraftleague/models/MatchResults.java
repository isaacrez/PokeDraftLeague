/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

/**
 *
 * @author isaacrez
 */
public class MatchResults {
    
    private int matchId;
    private int teamId;
    private boolean wasWon;
    private int differential;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isWasWon() {
        return wasWon;
    }

    public void setWasWon(boolean wasWon) {
        this.wasWon = wasWon;
    }

    public int getDifferential() {
        return differential;
    }

    public void setDifferential(int differential) {
        this.differential = differential;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.matchId;
        hash = 79 * hash + this.teamId;
        hash = 79 * hash + (this.wasWon ? 1 : 0);
        hash = 79 * hash + this.differential;
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
        final MatchResults other = (MatchResults) obj;
        if (this.matchId != other.matchId) {
            return false;
        }
        if (this.teamId != other.teamId) {
            return false;
        }
        if (this.wasWon != other.wasWon) {
            return false;
        }
        if (this.differential != other.differential) {
            return false;
        }
        return true;
    }

}
