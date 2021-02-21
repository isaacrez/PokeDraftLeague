/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Pokemon;
import com.mycompany.pokedraftleague.models.Roster;
import com.mycompany.pokedraftleague.models.Team;
import java.util.List;

/**
 *
 * @author isaacrez
 */
public interface TeamDao {
    List<Team> getAllTeams();
    List<Team> getAllTeamsForLeague(int leagueId);
    Roster getRosterById(int teamId, int leagueId);
    List<Roster> getAllTeamRostersForLeague(int leagueId);
    Team getTeamById(int id);
    List<Team> getTeamsByMatchId(int id);
    Team getTeamOfPokemonInLeague(int pokeId, int leagueId);
    Team addTeam(Team team);
    void updateTeam(Team team);
    void deleteTeamById(int id);
}
