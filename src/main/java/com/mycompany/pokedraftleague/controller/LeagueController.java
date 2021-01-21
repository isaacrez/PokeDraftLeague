/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.LeagueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaacrez
 */
@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/league")
public class LeagueController {
    
    @Autowired
    private final LeagueDao leagueDao;
    
    public LeagueController (LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }
    
    @GetMapping()
    public ResponseEntity getAllLeagues() {
        return ResponseEntity.ok(leagueDao.getAllLeagues());
    }
}