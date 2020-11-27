package com.teammatch.tournament.controller.Tournament;

import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.service.PlayerService;
import com.teammatch.tournament.domain.service.ProfessionalTournamentService;
import com.teammatch.tournament.resource.*;
import com.teammatch.tournament.resource.Tournament.ProfessionalTournamentResource;
import com.teammatch.tournament.resource.Tournament.SaveProfessionalTournamentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "Professional Tournament", description = "Professional-Tournament API")
@RestController
@RequestMapping("/api")
public class ProfessionalTournamentPlayersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfessionalTournamentService professionalTournamentService;
    @Autowired
    private PlayerService playerService;

    @PostMapping("/professionalTournaments/{professionalTournamentId}/players/{playerId}")
    public ProfessionalTournamentResource assignProfessionalTournamentPlayer(
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(professionalTournamentService.assignProfessionalTournamentPlayer(professionalTournamentId, playerId));
    }

    @DeleteMapping("/professionalTournaments/{professionalTournamentId}/players/{playerId}")
    public ProfessionalTournamentResource unassignFreeTournamentPlayer(
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(professionalTournamentService.unassignProfessionalTournamentPlayer(professionalTournamentId, playerId));
    }


    @GetMapping("/professionalTournaments/{professionalTournamentId}/players")
    public Page<PlayerResource> getAllPlayersByProfessionalTournamentId(
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId,
            Pageable pageable) {
        List<PlayerResource> players = playerService.getAllPlayersByProfessionalTournamentId(professionalTournamentId,pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int playersCount = players.size();
        return new PageImpl<>(players, pageable, playersCount);
    }
    private ProfessionalTournament convertToEntity(SaveProfessionalTournamentResource resource) {
        return mapper.map(resource, ProfessionalTournament.class);
    }

    private ProfessionalTournamentResource convertToResource(ProfessionalTournament entity) {
        return mapper.map(entity, ProfessionalTournamentResource.class);
    }
    private Player convertToEntity(SavePlayerResource resource) {
        return mapper.map(resource, Player.class);
    }
    private PlayerResource convertToResource(Player entity) {
        return mapper.map(entity, PlayerResource.class);
    }
}
