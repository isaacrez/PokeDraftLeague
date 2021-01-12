/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Lineup;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface LineupDao {
    List<Lineup> getAllLineups();
    Lineup getLineupById(int id);
    Lineup addLineup(Lineup lineup);
    void updateLineup(Lineup lineup);
    void deleteLineupById(int id);
}
