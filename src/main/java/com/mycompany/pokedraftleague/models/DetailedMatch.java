/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author isaacrez
 */
public class DetailedMatch extends Match {
    
    private List<Lineup> participants;

    public List<Lineup> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Lineup> participants) {
        this.participants = participants;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.participants);
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
        final DetailedMatch other = (DetailedMatch) obj;
        if (!Objects.equals(this.participants, other.participants)) {
            return false;
        }
        return true;
    }
}