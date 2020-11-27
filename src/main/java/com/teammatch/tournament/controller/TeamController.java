package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Team;
import com.teammatch.tournament.domain.service.TeamService;
import com.teammatch.tournament.resource.Team.SaveTeamResource;
import com.teammatch.tournament.resource.Team.TeamResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public Page<TeamResource> getAllTeams(Pageable pageable) {

        Page<Team> teamsPage = teamService.getAllTeams(pageable);
        List<TeamResource> resources = teamsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/teams/{teamId}")
    public TeamResource getTeamById(@PathVariable(value = "teamId") Long teamId) {
        return convertToResource(teamService.getTeamById(teamId));
    }

    @PostMapping("/teams")
    public TeamResource createTeam(
            @Valid @RequestBody SaveTeamResource resource) {
        Team team = convertToEntity(resource);
        return convertToResource(teamService.createTeam(team));

    }


    @PutMapping("/teams/{teamId}")
    public TeamResource updateTeam(@PathVariable Long teamId,
                                   @Valid @RequestBody SaveTeamResource resource) {
        Team team = convertToEntity(resource);
        return convertToResource(
                teamService.updateTeam(teamId, team));
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
        return teamService.deleteTeam(teamId);
    }

    @GetMapping("/players/{playerId}/teams")
    public Page<TeamResource> getAllTeamsByPlayerId(
            @PathVariable(name = "playerId") Long playerId,
            Pageable pageable) {
        Page<Team> teamsPage = teamService.getAllTeamsByPlayerId(playerId, pageable);
        List<TeamResource> resources = teamsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }

    @PostMapping("/teams/{teamId}/players/{playerId}")
    public TeamResource assignTeamPlayer(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(teamService.assignTeamPlayer(teamId, playerId));
    }

    @DeleteMapping("/teams/{teamId}/players/{playerId}")
    public TeamResource unassignTeamPlayer(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(teamService.unassignTeamPlayer(teamId, playerId));
    }

    private Team convertToEntity(SaveTeamResource resource) {
        return mapper.map(resource,Team.class);
    }

    private TeamResource convertToResource(Team entity) {
        return mapper.map(entity, TeamResource.class);
    }



}
