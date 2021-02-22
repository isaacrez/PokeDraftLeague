/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data.match;

import com.mycompany.pokedraftleague.models.Match;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface MatchDao {
    List<Match> getAllMatches();
    Match getMatchById(int id);
    List<Match> getMatchesByLeagueId(int id);
    Match addMatch(Match match);
    void updateMatch(Match match);
    void deleteMatchById(int id);
}
