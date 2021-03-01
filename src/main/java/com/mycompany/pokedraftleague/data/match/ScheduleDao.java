/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.match;

import com.mycompany.pokedraftleague.models.MinimumMatch;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface ScheduleDao {
    MinimumMatch getMatchById(int id);
    List<MinimumMatch> getScheduleForLeague(int id);
}
