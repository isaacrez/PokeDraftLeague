/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.MatchDao;
import com.mycompany.pokedraftleague.data.MatchResultsDao;
import com.mycompany.pokedraftleague.data.PokemonResultsDao;
import com.mycompany.pokedraftleague.data.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @GetMapping("/results/{matchId}/{teamId}")
    public ResponseEntity getMatchResultsFor(@PathVariable int matchId, @PathVariable int teamId) {
        return ResponseEntity.ok(matchResultsDao.getMatchResultsFor(matchId, teamId));
    }
    
    @GetMapping("/schedule/{leagueId}")
    public ResponseEntity getScheduleFor(@PathVariable int leagueId) {
        return ResponseEntity.ok(matchDao.getMatchesByLeagueId(leagueId));
    }
    
    @GetMapping("/teams/{matchId}")
    public ResponseEntity getTeamsIn(@PathVariable int matchId) {
        return ResponseEntity.ok(teamDao.getTeamsByMatchId(matchId));
    }
    
    @GetMapping("/lineup/{matchId}")
    public ResponseEntity getAllParticipantsIn(@PathVariable int matchId) {
        return ResponseEntity.ok(pokemonResultsDao.getAllPokemonInMatch(matchId));
    }
    
    @GetMapping("/lineup/{matchId}/{teamId}")
    public ResponseEntity getParticipantsIn(@PathVariable int matchId, @PathVariable int teamId) {
        return ResponseEntity.ok(pokemonResultsDao.getAllPokemonInMatch(matchId));
    }
    
    private ResponseEntity wrapInEntity(Object input) {
        if (input == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(input);
        }
    }
}
