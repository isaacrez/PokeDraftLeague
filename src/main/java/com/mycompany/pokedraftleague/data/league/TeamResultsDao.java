/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.league;

import com.mycompany.pokedraftleague.models.TeamResults;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface TeamResultsDao {
    TeamResults getTeamResultsFor(int teamId, int leagueId);
    List<TeamResults> getTeamResultsForLeague(int leagueId);
}
