package com.teammatch.tournament.domain.service;

import com.teammatch.tournament.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TeamService {
    Page<Team> getAllTeams(Pageable pageable);
    Team getTeamById(Long teamId);
    Team createTeam(Team team);
    Team updateTeam(Long teamId,Team teamRequest);
    ResponseEntity<?> deleteTeam(Long teamId);
    Team assignTeamPlayer(Long teamId, Long playerId);
    Team unassignTeamPlayer(Long teamId, Long playerId);
    Page<Team> getAllTeamsByPlayerId(Long playerId, Pageable pageable);
    Team getTeamByName(String name);
}
