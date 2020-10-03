package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Tournament;
import com.teammatch.tournament.domain.service.TournamentService;
import com.teammatch.tournament.resource.SaveTournamentResource;
import com.teammatch.tournament.resource.TournamentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Tournaments", description = "Tournaments API")
@RestController
@RequestMapping("/api")
public class TournamentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/tournaments")
    public Page<TournamentResource> getAllTournaments(Pageable pageable){
        Page<Tournament> tournamentsPage=tournamentService.getAllTournaments(pageable);
        List<TournamentResource> resources = tournamentsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/tournaments/{tournamentId}")
    public TournamentResource getTournamentById(@PathVariable (value = "tournamentId") Long tournamentId){
        return convertToResource(tournamentService.getTournamentById(tournamentId));
    }

    @PostMapping("/tournaments")
    public TournamentResource createTournament(
            @Valid @RequestBody SaveTournamentResource resource){
        Tournament tournament = convertToEntity(resource);
        return convertToResource(tournamentService.createTournament(tournament));
    }

    @PutMapping("/tournaments/{tournamentId}")
    public TournamentResource updateTournament(@PathVariable (value = "tournamentId") Long tournamentId,
                                               @Valid @RequestBody SaveTournamentResource resource){
        Tournament tournament=convertToEntity(resource);
        return convertToResource(tournamentService.updateTournament(tournamentId, tournament));
    }

    @DeleteMapping("/tournaments/{tournamentId}")
    public ResponseEntity<?> deleteTournament(@PathVariable (value = "tournamentId") Long tournamentId){
        return tournamentService.deleteTournament(tournamentId);
    }

    private Tournament convertToEntity(SaveTournamentResource resource) {
        return mapper.map(resource, Tournament.class);
    }

    private TournamentResource convertToResource(Tournament entity) {
        return mapper.map(entity, TournamentResource.class);
    }
}
