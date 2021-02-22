/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.pokemon.AggregatePokemonStatsDao;
import com.mycompany.pokedraftleague.data.pokemon.PokemonDao;
import com.mycompany.pokedraftleague.data.pokemon.DetailedPokemonDao;
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
@RequestMapping("/api/pokemon")
public class PokemonController {
    
    private final PokemonDao pokemonDao;
    private final DetailedPokemonDao detailedPokemonDao;
    private final AggregatePokemonStatsDao aggregatePokemonStatsDao;
    
    @Autowired
    public PokemonController (PokemonDao pokemonDao,
            DetailedPokemonDao detailedPokemonDao,
            AggregatePokemonStatsDao aggregatePokemonStatsDao) {
        this.pokemonDao = pokemonDao;
        this.detailedPokemonDao = detailedPokemonDao;
        this.aggregatePokemonStatsDao = aggregatePokemonStatsDao;
    }
    
    @GetMapping()
    public ResponseEntity getPokemon() {
        return ResponseEntity.ok(pokemonDao.getAllPokemon());
    }
    
    @GetMapping("/full")
    public ResponseEntity getSlice(@RequestParam int leagueId,
                                   @RequestParam Optional<Integer> limit,
                                   @RequestParam Optional<Integer> offset) {
        return ResponseEntity.ok(detailedPokemonDao.getSliceOfPokemon(
                leagueId,
                limit.orElseGet(() -> 5),
                offset.orElseGet(() -> 0)));
    }
    
    @GetMapping("/team")
    public ResponseEntity getPokemonOn(@RequestParam int teamId,
                                       @RequestParam int leagueId) {
        return ResponseEntity.ok(detailedPokemonDao.getPokemonFromRoster(teamId, leagueId));
    }
    
    @GetMapping("/stats")
    public ResponseEntity getPokeStatsForLeague(@RequestParam int pokeId,
                                                @RequestParam int leagueId,
                                                @RequestParam Optional<Integer> teamId) {
        if (teamId.isEmpty()) {
            return ResponseEntity.ok(aggregatePokemonStatsDao.getStatsFor(pokeId, leagueId));
        } else {
            return ResponseEntity.ok(aggregatePokemonStatsDao.getStatsFor(pokeId, teamId.get(), leagueId));
        }
    }    
}
