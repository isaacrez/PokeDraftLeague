/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.MatchResults;

/**
 *
 * @author isaacrez
 */
public interface MatchResultsDao {
    MatchResults getMatchResultsFor(MatchResults matchResults);
}
