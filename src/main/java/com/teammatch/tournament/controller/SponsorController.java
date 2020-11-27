package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.model.Sponsor;
import com.teammatch.tournament.domain.service.SponsorService;
import com.teammatch.tournament.resource.Sponsor.SaveSponsorResource;
import com.teammatch.tournament.resource.Sponsor.SponsorResource;
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

@RestController
@RequestMapping("/api")
public class SponsorController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SponsorService sponsorService;

    @GetMapping("/sponsors")
    public Page<SponsorResource> getAllSponsors(Pageable pageable) {

        Page<Sponsor> sponsorsPage = sponsorService.getAllSponsors(pageable);
        List<SponsorResource> resources = sponsorsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/sponsors/{sponsorId}")
    public SponsorResource getSponsorById(@PathVariable(value = "sponsorId") Long sponsorId) {
        return convertToResource(sponsorService.getSponsorById(sponsorId));
    }

    @PostMapping("/sponsors")
    public SponsorResource createSponsor(
            @Valid @RequestBody SaveSponsorResource resource) {
        Sponsor sponsor = convertToEntity(resource);
        return convertToResource(sponsorService.createSponsor(sponsor));

    }

    @PutMapping("/sponsors/{sponsorId}")
    public SponsorResource updateSponsor(@PathVariable Long sponsorId,
                                   @Valid @RequestBody SaveSponsorResource resource) {
        Sponsor sponsor = convertToEntity(resource);
        return convertToResource(
                sponsorService.updateSponsor(sponsorId, sponsor));
    }

    @DeleteMapping("/sponsors/{sponsorId}")
    public ResponseEntity<?> deleteSponsor(@PathVariable Long sponsorId) {
        return sponsorService.deleteSponsor(sponsorId);
    }

    @GetMapping("/professional-tournaments/{professionalTournamentId}/sponsors")
    public Page<SponsorResource> getAllSponsorsByTagId(
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId,
            Pageable pageable) {
        Page<Sponsor> sponsorsPage = sponsorService.getAllSponsorsByProfessionalTournamentId(professionalTournamentId, pageable);
        List<SponsorResource> resources = sponsorsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }

    @PostMapping("/sponsors/{sponsorId}/professional-tournaments/{professionalTournamentId}")
    public SponsorResource assignSponsorProfessionalTournament(
            @PathVariable(name = "sponsorId") Long sponsorId,
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId) {
        return convertToResource(sponsorService.assignSponsorProfessionalTournament(sponsorId, professionalTournamentId));
    }

    @DeleteMapping("/sponsors/{sponsorId}/professional-tournaments/{professionalTournamentId}")
    public SponsorResource unassignSponsorProfessionalTournament(
            @PathVariable(name = "sponsorId") Long sponsorId,
            @PathVariable(name = "professionalTournamentId") Long professionalTournamentId) {
        return convertToResource(sponsorService.unassignSponsorProfessionalTournament(sponsorId, professionalTournamentId));
    }

    private Sponsor convertToEntity(SaveSponsorResource resource) {
        return mapper.map(resource, Sponsor.class);
    }

    private SponsorResource convertToResource(Sponsor entity) {
        return mapper.map(entity, SponsorResource.class);
    }

}
