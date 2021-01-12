/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Match;
import com.mycompany.pokedraftleague.models.MatchResults;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface MatchDao {
    List<Match> getAllMatches();
    Match getMatchById(int id);
    MatchResults getMatchResultsById(int id);
    Match addMatch(Match match);
    void updateMatch(Match match);
    void deleteMatchById(int id);
}
