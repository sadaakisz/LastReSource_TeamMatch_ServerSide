package com.teammatch.tournament.controller.Tournament;

import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.service.ProfessionalTournamentService;
import com.teammatch.tournament.resource.Tournament.ProfessionalTournamentResource;
import com.teammatch.tournament.resource.Tournament.SaveProfessionalTournamentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Professional Tournament", description = "Professional-Tournament API")
@RestController
@RequestMapping("/api")
public class PlayerProfessionalTournamentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfessionalTournamentService professionalTournamentService;

    @GetMapping("/player/{playerId}/professionalTournaments")
    public Page<ProfessionalTournamentResource> getAllProfessionalTournamentsByPlayerId(
            @PathVariable(name = "playerId") Long playerId,
            Pageable pageable) {
        Page<ProfessionalTournament> professionalTournamentPage = professionalTournamentService.getAllProfessionalTournamentByPlayerId(playerId, pageable);
        List<ProfessionalTournamentResource> resources = professionalTournamentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }



    private ProfessionalTournament convertToEntity(SaveProfessionalTournamentResource resource) {
        return mapper.map(resource, ProfessionalTournament.class);
    }

    private ProfessionalTournamentResource convertToResource(ProfessionalTournament entity) {
        return mapper.map(entity, ProfessionalTournamentResource.class);
    }
}
