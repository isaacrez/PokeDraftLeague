/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;
import com.mycompany.pokedraftleague.models.TeamResults;

/**
 *
 * @author isaacrez
 */
public interface MatchResultsDao {
    MatchResults getMatchResultsFor(int matchId, int teamId);
    TeamResults getTeamResultsFor(int teamId, int leagueId);
}
