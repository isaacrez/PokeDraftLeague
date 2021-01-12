/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Lineup;
import com.mycompany.pokedraftleague.models.Team;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface TeamDao {
    List<Team> getAllTeams();
    List<Team> getAllTeamsForLeague(int leagueId);
    Lineup getRosterById(int id);
    Team getTeamById(int id);
    Team addTeam(Team team);
    void updateTeam(Team team);
    void deleteTeamById(int id);
}
