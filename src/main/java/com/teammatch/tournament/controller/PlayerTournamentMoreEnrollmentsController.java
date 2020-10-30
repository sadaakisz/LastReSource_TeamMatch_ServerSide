package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.ProfessionalTournament;
import com.teammatch.tournament.domain.model.TournamentMoreEnrollment;
import com.teammatch.tournament.domain.service.ProfessionalTournamentService;
import com.teammatch.tournament.domain.service.TournamentMoreEnrollmentService;
import com.teammatch.tournament.resource.ProfessionalTournamentResource;
import com.teammatch.tournament.resource.SaveProfessionalTournamentResource;
import com.teammatch.tournament.resource.SaveTournamentMoreEnrollmentResource;
import com.teammatch.tournament.resource.TournamentMoreEnrollmentResource;
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

@Tag(name = "Tournament More Enrollment", description = "Tournament More Enrollment API")
@RestController
@RequestMapping("/api")
public class PlayerTournamentMoreEnrollmentsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TournamentMoreEnrollmentService tournamentMoreEnrollmentService;

    @GetMapping("/player/{playerId}/tournament-more-enrollments")
    public Page<TournamentMoreEnrollmentResource> getAllTournamentMoreEnrollmentsByPlayerId(
            @PathVariable(name = "playerId") Long playerId,
            Pageable pageable) {
        Page<TournamentMoreEnrollment> tournamentMoreEnrollmentPage = tournamentMoreEnrollmentService.getAllTournamentMoreEnrollmentsByPlayerId(playerId, pageable);
        List<TournamentMoreEnrollmentResource> resources = tournamentMoreEnrollmentPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }



    private TournamentMoreEnrollment convertToEntity(SaveTournamentMoreEnrollmentResource resource) {
        return mapper.map(resource, TournamentMoreEnrollment.class);
    }

    private TournamentMoreEnrollmentResource convertToResource(TournamentMoreEnrollment entity) {
        return mapper.map(entity, TournamentMoreEnrollmentResource.class);
    }


}
