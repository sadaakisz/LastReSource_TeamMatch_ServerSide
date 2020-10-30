package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.resource.FreeTournamentResource;
import com.teammatch.tournament.resource.SaveFreeTournamentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlayerFreeTournamentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FreeTournamentService freeTournamentService;

    @GetMapping("/player/{playerId}/freeTournaments")
    public Page<FreeTournamentResource> getAllFreeTournamentsByPlayerId(
            @PathVariable(name = "playerId") Long playerId,
            Pageable pageable) {
        Page<FreeTournament> freeTournamentPage = freeTournamentService.getAllFreeTournamentsByPlayerId(playerId, pageable);
        List<FreeTournamentResource> resources = freeTournamentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }



    private FreeTournament convertToEntity(SaveFreeTournamentResource resource) {
        return mapper.map(resource, FreeTournament.class);
    }

    private FreeTournamentResource convertToResource(FreeTournament entity) {
        return mapper.map(entity, FreeTournamentResource.class);
    }
}
