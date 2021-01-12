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
public class Pokemon {
    
    private String name;
    private String form;
    private int directKOs;
    private int indirectKOs;
    private boolean died;

    public void setName(String name) {
        this.name = name;
    }

    public void setForm(String form) {
        this.form = form;
    }
    
    public String getUrlID() {
        if (form != null && !form.isEmpty()) {
            return name + "-" + form;
        } else {
            return name;
        }
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

    public boolean isDied() {
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.form);
        hash = 97 * hash + this.directKOs;
        hash = 97 * hash + this.indirectKOs;
        hash = 97 * hash + (this.died ? 1 : 0);
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
        final Pokemon other = (Pokemon) obj;
        if (this.directKOs != other.directKOs) {
            return false;
        }
        if (this.indirectKOs != other.indirectKOs) {
            return false;
        }
        if (this.died != other.died) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.form, other.form)) {
            return false;
        }
        return true;
    }
    
}
