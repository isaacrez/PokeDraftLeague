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
    
    private int HP;
    private int Atk;
    private int Def;
    private int SpA;
    private int SpD;
    private int Spe;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAtk() {
        return Atk;
    }

    public void setAtk(int Atk) {
        this.Atk = Atk;
    }

    public int getDef() {
        return Def;
    }

    public void setDef(int Def) {
        this.Def = Def;
    }

    public int getSpA() {
        return SpA;
    }

    public void setSpA(int SpA) {
        this.SpA = SpA;
    }

    public int getSpD() {
        return SpD;
    }

    public void setSpD(int SpD) {
        this.SpD = SpD;
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
        hash = 89 * hash + this.HP;
        hash = 89 * hash + this.Atk;
        hash = 89 * hash + this.Def;
        hash = 89 * hash + this.SpA;
        hash = 89 * hash + this.SpD;
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
        if (this.HP != other.HP) {
            return false;
        }
        if (this.Atk != other.Atk) {
            return false;
        }
        if (this.Def != other.Def) {
            return false;
        }
        if (this.SpA != other.SpA) {
            return false;
        }
        if (this.SpD != other.SpD) {
            return false;
        }
        if (this.Spe != other.Spe) {
            return false;
        }
        return true;
    }
}
