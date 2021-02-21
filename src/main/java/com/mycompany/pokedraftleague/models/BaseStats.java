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
public class BaseStats {
    
    private int hp;
    private int atk;
    private int def;
    private int spAtk;
    private int SpDef;
    private int Spe;

    public int getHP() {
        return hp;
    }

    public void setHP(int HP) {
        this.hp = HP;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int Atk) {
        this.atk = Atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int Def) {
        this.def = Def;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return SpDef;
    }

    public void setSpDef(int SpDef) {
        this.SpDef = SpDef;
    }

    public int getSpe() {
        return Spe;
    }

    public void setSpe(int Spe) {
        this.Spe = Spe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.hp;
        hash = 89 * hash + this.atk;
        hash = 89 * hash + this.def;
        hash = 89 * hash + this.spAtk;
        hash = 89 * hash + this.SpDef;
        hash = 89 * hash + this.Spe;
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
        final BaseStats other = (BaseStats) obj;
        if (this.hp != other.hp) {
            return false;
        }
        if (this.atk != other.atk) {
            return false;
        }
        if (this.def != other.def) {
            return false;
        }
        if (this.spAtk != other.spAtk) {
            return false;
        }
        if (this.SpDef != other.SpDef) {
            return false;
        }
        if (this.Spe != other.Spe) {
            return false;
        }
        return true;
    }
}
