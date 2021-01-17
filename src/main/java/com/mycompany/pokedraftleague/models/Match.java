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
    
    private int id;
    
    private Team team;
    private Lineup teamLineup;
    
    private String status;
    private Date completedOn;
    private int scheduledWeek;

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

    public Lineup getTeamLineup() {
        return teamLineup;
    }

    public void setTeamLineup(Lineup teamLineup) {
        this.teamLineup = teamLineup;
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
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.team);
        hash = 23 * hash + Objects.hashCode(this.teamLineup);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.completedOn);
        hash = 23 * hash + this.scheduledWeek;
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
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        if (!Objects.equals(this.teamLineup, other.teamLineup)) {
            return false;
        }
        if (!Objects.equals(this.completedOn, other.completedOn)) {
            return false;
        }
        return true;
    }
    
}
