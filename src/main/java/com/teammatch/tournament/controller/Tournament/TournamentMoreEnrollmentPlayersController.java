package com.teammatch.tournament.controller.Tournament;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import com.teammatch.tournament.domain.service.PlayerService;
import com.teammatch.tournament.domain.service.TournamentMoreEnrollmentService;
import com.teammatch.tournament.resource.*;
import com.teammatch.tournament.resource.Tournament.SaveTournamentMoreEnrollmentResource;
import com.teammatch.tournament.resource.Tournament.TournamentMoreEnrollmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tournament More Enrollment", description = "Tournament More Enrollment API")
@RestController
@RequestMapping("/api")
public class TournamentMoreEnrollmentPlayersController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TournamentMoreEnrollmentService tournamentMoreEnrollmentService;
    @Autowired
    private PlayerService playerService;


    @PostMapping("/tournaments-more-enrollments/{tournamentMoreEnrollmentId}/players/{playerId}")
    public TournamentMoreEnrollmentResource assignTournamentMoreProfessionalPlayer(
            @PathVariable(name = "tournamentMoreEnrollmentId") Long tournamentMoreEnrollmentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(tournamentMoreEnrollmentService.assignTournamentMoreEnrollmentPlayer(tournamentMoreEnrollmentId, playerId));
    }

    @DeleteMapping("/tournaments-more-enrollments/{tournamentMoreEnrollmentId}/players/{playerId}")
    public TournamentMoreEnrollmentResource unassignTournamentMoreEnrollmentPlayer(
            @PathVariable(name = "tournamentMoreEnrollmentId") Long tournamentMoreEnrollmentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(tournamentMoreEnrollmentService.unassignTournamentMoreEnrollmentPlayer(tournamentMoreEnrollmentId, playerId));
    }


    @GetMapping("/tournaments-more-enrollments/{tournamentMoreEnrollmentId}/players")
    public Page<PlayerResource> getAllPlayersByTournamentMoreEnrollmentId(
            @PathVariable(name = "tournamentMoreEnrollmentId") Long tournamentMoreEnrollmentId,
            Pageable pageable) {
        List<PlayerResource> players = playerService.getAllPlayersByTournamentMoreEnrollmentId(tournamentMoreEnrollmentId,pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int playersCount = players.size();
        return new PageImpl<>(players, pageable, playersCount);
    }
    private TournamentMoreEnrollment convertToEntity(SaveTournamentMoreEnrollmentResource resource) {
        return mapper.map(resource, TournamentMoreEnrollment.class);
    }

    private TournamentMoreEnrollmentResource convertToResource(TournamentMoreEnrollment entity) {
        return mapper.map(entity, TournamentMoreEnrollmentResource.class);
    }
    private Player convertToEntity(SavePlayerResource resource) {
        return mapper.map(resource, Player.class);
    }
    private PlayerResource convertToResource(Player entity) {
        return mapper.map(entity, PlayerResource.class);
    }

}
