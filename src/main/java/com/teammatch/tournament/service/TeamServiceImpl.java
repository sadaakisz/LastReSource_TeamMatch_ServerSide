package com.teammatch.tournament.service;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.Team;
import com.teammatch.tournament.domain.repository.PlayerRepository;
import com.teammatch.tournament.domain.repository.TeamRepository;
import com.teammatch.tournament.domain.service.TeamService;
import com.teammatch.tournament.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team", "Id", teamId));
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Long teamId, Team teamRequest) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team", "Id", teamId));
        return teamRepository.save(
                team.setName(teamRequest.getName())
                        .setHoursPlayed(teamRequest.getHoursPlayed())
                        .setTeamSize(teamRequest.getTeamSize())
                        .setLevelAverage(teamRequest.getLevelAverage()));
    }

    @Override
    public ResponseEntity<?> deleteTeam(Long teamId) {
       Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Team", "Id", teamId));
        teamRepository.delete(team);
        return ResponseEntity.ok().build();
    }

    @Override
    public Team assignTeamPlayer(Long teamId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Player", "Id", playerId));
        return teamRepository.findById(teamId).map(team -> {
            return teamRepository.save(team.teamWith(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Team", "Id", teamId));

    }

    @Override
    public Team unassignTeamPlayer(Long teamId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Player", "Id", playerId));
        return teamRepository.findById(teamId).map(team -> {
            return teamRepository.save(team.unTeamWith(player));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Team", "Id", teamId));
    }

    @Override
    public Page<Team> getAllTeamsByPlayerId(Long playerId, Pageable pageable) {
        return playerRepository.findById(playerId).map( player -> {
            List<Team> teams = player.getTeams();
            int teamsCount = teams.size();
            return new PageImpl<>(teams, pageable, teamsCount);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Player", "Id", playerId));
    }

    @Override
    public Team getTeamByName(String name) {
        return teamRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "Name", name));
    }

}
