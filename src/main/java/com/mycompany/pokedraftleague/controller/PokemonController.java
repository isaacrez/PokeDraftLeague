/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.PokemonDao;
import com.mycompany.pokedraftleague.data.PokemonResultsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaacrez
 */
@RestController
@RequestMapping("/api")
public class PokemonController {
    
    @Autowired
    private final PokemonDao pokemonDao;
    
    @Autowired
    private final PokemonResultsDao pokemonResultsDao;
    
    public PokemonController (PokemonDao pokemonDao, PokemonResultsDao pokemonResultsDao) {
        this.pokemonDao = pokemonDao;
        this.pokemonResultsDao = pokemonResultsDao;
    }
    
    @GetMapping("forms/{pokeName}")
    public ResponseEntity templateCall2(@PathVariable String pokeName) {
        return ResponseEntity.ok(pokemonDao.getFormsFor(pokeName));
    }
    
}
