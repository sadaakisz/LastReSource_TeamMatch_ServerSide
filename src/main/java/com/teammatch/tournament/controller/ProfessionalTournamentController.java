package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.domain.service.ProfessionalTournamentService;
import com.teammatch.tournament.resource.FreeTournamentResource;
import com.teammatch.tournament.resource.ProfessionalTournamentResource;
import com.teammatch.tournament.resource.SaveFreeTournamentResource;
import com.teammatch.tournament.resource.SaveProfessionalTournamentResource;
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

@Tag(name = "Professional Tournament", description = "Professional-Tournament API")
@RestController
@RequestMapping("/api")
public class ProfessionalTournamentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfessionalTournamentService professionalTournamentService;

    @GetMapping("/organizers/{organizerId}/professional-tournaments")
    public Page<ProfessionalTournamentResource> getAllProfessionalTournamentsByOrganizerId(@PathVariable(value = "organizerId") Long organizerId, Pageable pageable){
        Page<ProfessionalTournament> professionalTournamentsPage= professionalTournamentService.getAllProfessionalTournamentsByOrganizerId(organizerId, pageable);
        List<ProfessionalTournamentResource> resources =professionalTournamentsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/organizers/{organizerId}/professional-tournaments/{professionalTournamentId}")
    public ProfessionalTournamentResource getProfessionalTournamentByIdAndOrganizerId(@PathVariable(value = "organizerId") Long organizerId, @PathVariable (value = "professionalTournamentId") Long tournamentId){
        return convertToResource(professionalTournamentService.getProfessionalTournamentByIdAndOrganizerId(organizerId, tournamentId));
    }

    @PostMapping("/organizers/{organizerId}/professional-tournaments")
    public ProfessionalTournamentResource createProfessionalTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                       @Valid @RequestBody SaveProfessionalTournamentResource resource){
        ProfessionalTournament professionalTournament = convertToEntity(resource);
        return convertToResource(professionalTournamentService.createProfessionalTournament(organizerId, professionalTournament));
    }

    @PutMapping("/organizers/{organizerId}/professional-tournaments/{professionalTournamentId}")
    public ProfessionalTournamentResource updateProfessionalTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                   @PathVariable (value = "professionalTournamentId") Long tournamentId,
                                                   @Valid @RequestBody SaveProfessionalTournamentResource resource){
        ProfessionalTournament professionalTournament=convertToEntity(resource);
        return convertToResource(professionalTournamentService.updateProfessionalTournament(organizerId, tournamentId, professionalTournament));
    }

    @DeleteMapping("/organizers/{organizerId}/professional-tournaments/{professionalTournamentId}")
    public ResponseEntity<?> deleteFreeTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                  @PathVariable (value = "professionalTournamentId") Long professionalTournamentId){
        return professionalTournamentService.deleteProfessionalTournament(organizerId, professionalTournamentId);
    }

    private ProfessionalTournament convertToEntity(SaveProfessionalTournamentResource resource) {
        return mapper.map(resource, ProfessionalTournament.class);
    }

    private ProfessionalTournamentResource convertToResource(ProfessionalTournament entity) {
        return mapper.map(entity, ProfessionalTournamentResource.class);
    }
}
