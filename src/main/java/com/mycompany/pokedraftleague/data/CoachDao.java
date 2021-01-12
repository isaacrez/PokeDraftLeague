/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Coach;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface CoachDao {
    List<Coach> getAllCoaches();
    Coach getCoachById(int id);
    Coach addCoach(Coach coach);
    void updateCoach(Coach coach);
    void deleteCoachById(int id);
}
