/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class Match {
    
    private Team homeTeam;
    private Lineup homeTeamLineup;
    
    private Team awayTeam;
    private Lineup awayTeamLineup;
    
    private String status;
    private Date completedOn;
    private int scheduledWeek;

    public Team getHomeTeamName() {
        return homeTeam;
    }

    public void setHomeTeamName(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Lineup getHomeTeamLineup() {
        return homeTeamLineup;
    }

    public void setHomeTeamLineup(Lineup homeTeamLineup) {
        this.homeTeamLineup = homeTeamLineup;
    }

    public Team getAwayTeamName() {
        return awayTeam;
    }

    public void setAwayTeamName(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Lineup getAwayTeamLineup() {
        return awayTeamLineup;
    }

    public void setAwayTeamLineup(Lineup awayTeamLineup) {
        this.awayTeamLineup = awayTeamLineup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public int getScheduledWeek() {
        return scheduledWeek;
    }

    public void setScheduledWeek(int scheduledWeek) {
        this.scheduledWeek = scheduledWeek;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.homeTeam);
        hash = 37 * hash + Objects.hashCode(this.homeTeamLineup);
        hash = 37 * hash + Objects.hashCode(this.awayTeam);
        hash = 37 * hash + Objects.hashCode(this.awayTeamLineup);
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + Objects.hashCode(this.completedOn);
        hash = 37 * hash + this.scheduledWeek;
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
        if (this.scheduledWeek != other.scheduledWeek) {
            return false;
        }
        if (!Objects.equals(this.homeTeam, other.homeTeam)) {
            return false;
        }
        if (!Objects.equals(this.awayTeam, other.awayTeam)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.homeTeamLineup, other.homeTeamLineup)) {
            return false;
        }
        if (!Objects.equals(this.awayTeamLineup, other.awayTeamLineup)) {
            return false;
        }
        if (!Objects.equals(this.completedOn, other.completedOn)) {
            return false;
        }
        return true;
    }
    
}
