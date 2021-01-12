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
public class Coach {
    
    private int id;
    private String nickname;
    private String discordName;
    private String showdownName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public String getShowdownName() {
        return showdownName;
    }

    public void setShowdownName(String showdownName) {
        this.showdownName = showdownName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nickname);
        hash = 29 * hash + Objects.hashCode(this.discordName);
        hash = 29 * hash + Objects.hashCode(this.showdownName);
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
        final Coach other = (Coach) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.discordName, other.discordName)) {
            return false;
        }
        if (!Objects.equals(this.showdownName, other.showdownName)) {
            return false;
        }
        return true;
    }
    
}
