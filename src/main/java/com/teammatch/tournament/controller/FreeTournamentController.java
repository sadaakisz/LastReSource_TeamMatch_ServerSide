package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.Tournament;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.resource.SaveFreeTournamentResource;
import com.teammatch.tournament.resource.FreeTournamentResource;
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

@Tag(name = "Free Tournament", description = "Free Tournament API")
@RestController
@RequestMapping("/api")
public class FreeTournamentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FreeTournamentService freeTournamentService;

    @GetMapping("/organizers/{organizerId}/free-tournaments")
    public Page<FreeTournamentResource> getAllFreeTournamentsByOrganizerId(@PathVariable(value = "organizerId") Long organizerId, Pageable pageable){
        Page<FreeTournament> tournamentsPage= freeTournamentService.getAllFreeTournamentsByOrganizerId(organizerId, pageable);
        List<FreeTournamentResource> resources = tournamentsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/organizers/{organizerId}/free-tournaments/{freeTournamentId}")
    public FreeTournamentResource getFreeTournamentByIdAndOrganizerId(@PathVariable(value = "organizerId") Long organizerId, @PathVariable (value = "freeTournamentId") Long tournamentId){
        return convertToResource(freeTournamentService.getFreeTournamentByIdAndOrganizerId(organizerId, tournamentId));
    }

    @PostMapping("/organizers/{organizerId}/free-tournaments")
    public FreeTournamentResource createFreeTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                   @Valid @RequestBody SaveFreeTournamentResource resource){
        FreeTournament freeTournament = convertToEntity(resource);
        return convertToResource(freeTournamentService.createFreeTournament(organizerId, freeTournament));
    }

    @PutMapping("/organizers/{organizerId}/free-tournaments/{freeTournamentId}")
    public FreeTournamentResource updateTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                   @PathVariable (value = "freeTournamentId") Long tournamentId,
                                                   @Valid @RequestBody SaveFreeTournamentResource resource){
        FreeTournament freeTournament=convertToEntity(resource);
        return convertToResource(freeTournamentService.updateFreeTournament(organizerId, tournamentId, freeTournament));
    }

    @DeleteMapping("/organizers/{organizerId}/free-tournaments/{freeTournamentId}")
    public ResponseEntity<?> deleteFreeTournament(@PathVariable (value = "organizerId") Long organizerId,
            @PathVariable (value = "freeTournamentId") Long tournamentId){
        return freeTournamentService.deleteFreeTournament(organizerId, tournamentId);
    }

    private FreeTournament convertToEntity(SaveFreeTournamentResource resource) {
        return mapper.map(resource, FreeTournament.class);
    }

    private FreeTournamentResource convertToResource(FreeTournament entity) {
        return mapper.map(entity, FreeTournamentResource.class);
    }
}
