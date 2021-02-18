/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface MatchResultsDao {
    MatchResults getMatchResultsFor(int matchId, int teamId);
    List<MatchResults> getTeamResults(int teamId, int leagueId);
}
