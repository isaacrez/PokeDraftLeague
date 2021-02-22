/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.league;

import com.mycompany.pokedraftleague.models.League;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface LeagueDao {
    List<League> getAllLeagues();
    League getLeagueById(int id);
    League addLeague(League league);
    void updateLeague(League league);
    void deleteLeagueById(int id);
}
