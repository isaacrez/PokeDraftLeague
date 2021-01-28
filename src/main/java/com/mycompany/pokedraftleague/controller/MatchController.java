/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.MatchDao;
import com.mycompany.pokedraftleague.data.MatchResultsDao;
import com.mycompany.pokedraftleague.data.PokemonResultsDao;
import com.mycompany.pokedraftleague.models.MatchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    private final MatchDao matchDao;
    
    @Autowired
    private final MatchResultsDao matchResultsDao;
    
    @Autowired
    private final PokemonResultsDao pokemonResultsDao;
    
    public MatchController(MatchDao matchDao, MatchResultsDao matchResultsDao, PokemonResultsDao pokemonResultsDao) {
        this.matchDao = matchDao;
        this.matchResultsDao = matchResultsDao;
        this.pokemonResultsDao = pokemonResultsDao;
    }
    
    @GetMapping("/results")
    public ResponseEntity getMatchResultsFor(@RequestBody MatchResults matchResults) {
        return ResponseEntity.ok(matchResultsDao.getMatchResultsFor(matchResults));
    }
    
    @GetMapping("/results/{pokeId}/{leagueId}")
    public ResponseEntity getPokeStatsForLeague(@PathVariable int pokeId,
            @PathVariable int leagueId) {
        return ResponseEntity.ok(pokemonResultsDao.getResultsFor(pokeId, leagueId));
    }
    
    @GetMapping("/results/{pokeId}/{teamId}/{leagueId}")
    public ResponseEntity getPokeStatsOnTeamForLeague(@PathVariable int pokeId, 
            @PathVariable int teamId,
            @PathVariable int leagueId) {
        return wrapInEntity(pokemonResultsDao.getResultsFor(pokeId, teamId, leagueId));
    }
    
    @GetMapping("/teams/{matchId}")
    public ResponseEntity getTeamsIn(@PathVariable int matchId) {
        return ResponseEntity.ok(matchDao.getTeamsInMatch(matchId));
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
