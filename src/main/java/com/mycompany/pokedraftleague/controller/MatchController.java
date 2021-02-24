/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.match.MatchDao;
import com.mycompany.pokedraftleague.data.match.MatchResultsDao;
import com.mycompany.pokedraftleague.data.pokemon.PokemonResultsDao;
import com.mycompany.pokedraftleague.data.league.TeamDao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaacrez
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/match")
public class MatchController {
    
    private final MatchDao matchDao;
    private final TeamDao teamDao;
    private final MatchResultsDao matchResultsDao;
    private final PokemonResultsDao pokemonResultsDao;
    
    @Autowired
    public MatchController(MatchDao matchDao, TeamDao teamDao,
            MatchResultsDao matchResultsDao, PokemonResultsDao pokemonResultsDao) {
        this.matchDao = matchDao;
        this.teamDao = teamDao;
        this.matchResultsDao = matchResultsDao;
        this.pokemonResultsDao = pokemonResultsDao;
    }
    
    @GetMapping("/results")
    public ResponseEntity getMatchResultsFor(@RequestParam int matchId,
                                             @RequestParam int teamId) {
        return ResponseEntity.ok(matchResultsDao.getMatchResultsFor(matchId, teamId));
    }
    
    @GetMapping("/schedule")
    public ResponseEntity getScheduleFor(@RequestParam int leagueId) {
        return ResponseEntity.ok(matchDao.getMatchesByLeagueId(leagueId));
    }
    
    @GetMapping("/teams")
    public ResponseEntity getTeamsIn(@RequestParam int matchId) {
        return ResponseEntity.ok(teamDao.getTeamsByMatchId(matchId));
    }
    
    @GetMapping("/lineup")
    public ResponseEntity getAllParticipantsIn(@RequestParam int matchId,
                                               @RequestParam Optional<Integer> teamId) {
        if (teamId.isPresent()) {
            return ResponseEntity.ok(pokemonResultsDao.getPokemonInMatchFor(matchId, teamId.get()));
        } else {
            return ResponseEntity.ok(pokemonResultsDao.getAllPokemonInMatch(matchId));
        }
    }
}
