/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.PokemonDao;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/pokemon")
public class PokemonController {
    
    @Autowired
    private final PokemonDao pokemonDao;
    
    public PokemonController (PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }
    
    @GetMapping()
    public ResponseEntity getPokemon() {
        return ResponseEntity.ok(pokemonDao.getAllPokemon());
    }
    
    @GetMapping("forms/{pokeName}")
    public ResponseEntity getPokemonForms(@PathVariable String pokeName) {
        return ResponseEntity.ok(pokemonDao.getFormsFor(pokeName));
    }
    
}
