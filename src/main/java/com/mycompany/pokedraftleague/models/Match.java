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
    private String status;
    private Date dateSubmitted;
    private int scheduledWeek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.dateSubmitted);
        hash = 67 * hash + this.scheduledWeek;
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
        if (!Objects.equals(this.dateSubmitted, other.dateSubmitted)) {
            return false;
        }
        return true;
    }
}
