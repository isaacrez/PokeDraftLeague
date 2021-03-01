/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.controller;

import com.mycompany.pokedraftleague.data.league.LeagueDao;
import com.mycompany.pokedraftleague.data.league.TeamDao;
import com.mycompany.pokedraftleague.data.league.TeamResultsDao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author isaacrez
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/league")
public class LeagueController {
    
    private final LeagueDao leagueDao;
    private final TeamDao teamDao;
    private final TeamResultsDao teamResultsDao;

    @Autowired
    public LeagueController (LeagueDao leagueDao, TeamDao teamDao, TeamResultsDao teamResultsDao) {
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;
        this.teamResultsDao = teamResultsDao;
    }
    
    @GetMapping()
    public ResponseEntity getAllLeagues() {
        return ResponseEntity.ok(leagueDao.getAllLeagues());
    }
    
    @GetMapping("/{leagueId}")
    public ResponseEntity getLeagueById(@PathVariable int leagueId) {
        return ResponseEntity.ok(leagueDao.getLeagueById(leagueId));
    }
    
    @GetMapping("/roster/{leagueId}")
    public ResponseEntity getRostersForLeague(@PathVariable int leagueId) {
        return ResponseEntity.ok(teamDao.getAllTeamRostersForLeague(leagueId));
    }
    
    @GetMapping("/tiers")
    public ResponseEntity getTiersForLeague(@RequestParam int leagueId) {
        return ResponseEntity.ok(leagueDao.getTiersInLeague(leagueId));
    }
    
    @GetMapping("/results")
    public ResponseEntity getResultsForLeague(@RequestParam int leagueId,
                                              @RequestParam Optional<Integer> teamId) {
        if (teamId.isPresent()) {
            return ResponseEntity.ok(teamResultsDao.getTeamResultsFor(teamId.get(), leagueId));
        } else {
            return ResponseEntity.ok(teamResultsDao.getTeamResultsForLeague(leagueId));
        }
    }
}
