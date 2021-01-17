/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.MatchDao;
import com.mycompany.pokedraftleague.models.MatchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaacrez
 */
@RestController
@RequestMapping("/api/match")
public class MatchController {
    
    @Autowired
    private final MatchDao matchDao;
    
    public MatchController(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
    
    @GetMapping()
    public ResponseEntity getMatchResultsFor(@RequestBody MatchResults matchResults) {
        return ResponseEntity.ok(matchDao.getMatchResultsFor(matchResults));
    }
}
