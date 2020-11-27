package com.teammatch.tournament.controller.Tournament;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.Player;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.domain.service.PlayerService;
import com.teammatch.tournament.resource.Tournament.FreeTournamentResource;
import com.teammatch.tournament.resource.PlayerResource;
import com.teammatch.tournament.resource.Tournament.SaveFreeTournamentResource;
import com.teammatch.tournament.resource.SavePlayerResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "Free Tournament", description = "Free Tournament API")
@RestController
@RequestMapping("/api")
public class FreeTournamentPlayersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FreeTournamentService freeTournamentService;
    @Autowired
    private PlayerService playerService;

    @PostMapping("/freeTournaments/{freeTournamentId}/players/{playerId}")
    public FreeTournamentResource assignFreeTournamentPlayer(
            @PathVariable(name = "freeTournamentId") Long freeTournamentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(freeTournamentService.assignFreeTournamentPlayer(freeTournamentId, playerId));
    }

    @DeleteMapping("/freeTournaments/{freeTournamentId}/players/{playerId}")
    public FreeTournamentResource unassignFreeTournamentPlayer(
            @PathVariable(name = "freeTournamentId") Long freeTournamentId,
            @PathVariable(name = "playerId") Long playerId) {
        return convertToResource(freeTournamentService.unassignFreeTournamentPlayer(freeTournamentId, playerId));
    }


    @GetMapping("/freeTournaments/{freeTournamentId}/players")
    public Page<PlayerResource> getAllPlayersByFreeTournamentId(
            @PathVariable(name = "freeTournamentId") Long freeTournamentId,
            Pageable pageable) {
        List<PlayerResource> players = playerService.getAllPlayersByFreeTournamentId(freeTournamentId,pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int playersCount = players.size();
        return new PageImpl<>(players, pageable, playersCount);
    }
    private FreeTournament convertToEntity(SaveFreeTournamentResource resource) {
        return mapper.map(resource, FreeTournament.class);
    }

    private FreeTournamentResource convertToResource(FreeTournament entity) {
        return mapper.map(entity, FreeTournamentResource.class);
    }
    private Player convertToEntity(SavePlayerResource resource) {
        return mapper.map(resource, Player.class);
    }
    private PlayerResource convertToResource(Player entity) {
        return mapper.map(entity, PlayerResource.class);
    }
}
