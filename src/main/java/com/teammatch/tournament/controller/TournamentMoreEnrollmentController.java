package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.FreeTournament;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import com.teammatch.tournament.domain.service.FreeTournamentService;
import com.teammatch.tournament.domain.service.TournamentMoreEnrollmentService;
import com.teammatch.tournament.resource.FreeTournamentResource;
import com.teammatch.tournament.resource.SaveFreeTournamentResource;
import com.teammatch.tournament.resource.SaveTournamentMoreEnrollmentResource;
import com.teammatch.tournament.resource.TournamentMoreEnrollmentResource;
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

@Tag(name = "Tournament More Enrollment", description = "Tournament More Enrollment API")
@RestController
@RequestMapping("/api")
public class TournamentMoreEnrollmentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TournamentMoreEnrollmentService tournamentMoreEnrollmentService;

    @GetMapping("/organizers/{organizerId}/tournament-more-enrollments")
    public Page<TournamentMoreEnrollmentResource> getAllTournamentMoreEnrollmentsByOrganizerId(@PathVariable(value = "organizerId") Long organizerId, Pageable pageable){
        Page<TournamentMoreEnrollment> tournamentsPage= tournamentMoreEnrollmentService.getAllTournamentMoreEnrollmentsByOrganizerId(organizerId, pageable);
        List<TournamentMoreEnrollmentResource> resources = tournamentsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/organizers/{organizerId}/tournament-more-enrollments/{tournamentMoreEnrollmentId}")
    public TournamentMoreEnrollmentResource geTournamentMoreEnrollmentByIdAndOrganizerId(@PathVariable(value = "organizerId") Long organizerId, @PathVariable (value = "tournamentMoreEnrollmentId") Long tournamentId){
        return convertToResource(tournamentMoreEnrollmentService.getTournamentMoreEnrollmentByIdAndOrganizerId(organizerId, tournamentId));
    }

    @PostMapping("/organizers/{organizerId}/tournament-more-enrollments")
    public TournamentMoreEnrollmentResource createTournamentMoreEnrollment(@PathVariable (value = "organizerId") Long organizerId,
                                                       @Valid @RequestBody SaveTournamentMoreEnrollmentResource resource){
        TournamentMoreEnrollment tournamentMoreEnrollment = convertToEntity(resource);
        return convertToResource(tournamentMoreEnrollmentService.createTournamentMoreEnrollment(organizerId, tournamentMoreEnrollment));
    }

    @PutMapping("/organizers/{organizerId}/tournament-more-enrollments/{tournamentMoreEnrollmentId}")
    public TournamentMoreEnrollmentResource updateTournament(@PathVariable (value = "organizerId") Long organizerId,
                                                   @PathVariable (value = "tournamentMoreEnrollmentId") Long tournamentId,
                                                   @Valid @RequestBody SaveTournamentMoreEnrollmentResource resource){
        TournamentMoreEnrollment tournamentMoreEnrollment=convertToEntity(resource);
        return convertToResource(tournamentMoreEnrollmentService.updateTournamentMoreEnrollment(organizerId, tournamentId, tournamentMoreEnrollment));
    }

    @DeleteMapping("/organizers/{organizerId}/tournament-more-enrollments/{tournamentMoreEnrollmentId}")
    public ResponseEntity<?> deleteTournamentMoreEnrollment(@PathVariable (value = "organizerId") Long organizerId,
                                                  @PathVariable (value = "tournamentMoreEnrollmentId") Long tournamentId){
        return tournamentMoreEnrollmentService.deleteTournamentMoreEnrollment(organizerId, tournamentId);
    }

    private TournamentMoreEnrollment convertToEntity(SaveTournamentMoreEnrollmentResource resource) {
        return mapper.map(resource, TournamentMoreEnrollment.class);
    }

    private TournamentMoreEnrollmentResource convertToResource(TournamentMoreEnrollment entity) {
        return mapper.map(entity, TournamentMoreEnrollmentResource.class);
    }
}
