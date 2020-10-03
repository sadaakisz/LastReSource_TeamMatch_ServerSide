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

    @GetMapping("/organizers/{organizerId}/tournaments")
    public Page<TournamentResource> getAllTournamentsByOrganizerId(@PathVariable(value = "organizerId") Long organizerId, Pageable pageable){
        Page<Tournament> tournamentsPage=tournamentService.getAllTournamentsByOrganizerId(organizerId, pageable);
        List<TournamentResource> resources = tournamentsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/organizers/{organizerId}/tournaments/{tournamentId}")
    public TournamentResource getTournamentByIdAndOrganizerId(@PathVariable(value = "organizerId") Long organizerId, @PathVariable (value = "tournamentId") Long tournamentId){
        return convertToResource(tournamentService.getTournamentByIdAndOrganizerId(organizerId, tournamentId));
    }

    @PostMapping("/organizers/{organizerId}/tournaments")
    public TournamentResource createTournament(@PathVariable (value = "organizerId") Long organizerId,
            @Valid @RequestBody SaveTournamentResource resource){
        Tournament tournament = convertToEntity(resource);
        return convertToResource(tournamentService.createTournament(organizerId, tournament));
    }

    @PutMapping("/organizers/{organizerId}/tournaments/{tournamentId}")
    public TournamentResource updateTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                @PathVariable (value = "tournamentId") Long tournamentId,
                                               @Valid @RequestBody SaveTournamentResource resource){
        Tournament tournament=convertToEntity(resource);
        return convertToResource(tournamentService.updateTournament(organizerId, tournamentId, tournament));
    }

    @DeleteMapping("/organizers/{organizerId}/tournaments/{tournamentId}")
    public ResponseEntity<?> deleteTournament(@PathVariable (value = "organizerId") Long organizerId,
            @PathVariable (value = "tournamentId") Long tournamentId){
        return tournamentService.deleteTournament(organizerId, tournamentId);
    }

    private Tournament convertToEntity(SaveTournamentResource resource) {
        return mapper.map(resource, Tournament.class);
    }

    private TournamentResource convertToResource(Tournament entity) {
        return mapper.map(entity, TournamentResource.class);
    }
}
